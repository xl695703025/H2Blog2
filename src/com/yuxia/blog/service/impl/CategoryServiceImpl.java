package com.yuxia.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuxia.blog.entity.Article;
import com.yuxia.blog.entity.Category;
import com.yuxia.blog.mapper.ArticleMapper;
import com.yuxia.blog.mapper.CategoryMapper;
import com.yuxia.blog.mapper.CommentMapper;
import com.yuxia.blog.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private ArticleMapper articleMaper;
	@Autowired
	private CommentMapper commentMapper;

	@Override
	public List<Category> getCategoryList(String userName) {
		return categoryMapper.selectCategoryByUserName(userName);
	}

	/**
	 * 删除分类
	 */
	@Override
	public int delCategoryById(Integer categoryId, String userName) {
		Category category = categoryMapper.selectCategoryById(categoryId);
		List<Article> articleList = articleMaper.selectArticleByCategoryId(userName, 0, Integer.MAX_VALUE, categoryId);
		if (articleList.size() > 0) {// 如果分类中有文章，对文章进行相应的
			if (category.getCategoryName().equals("未分类")) {// 删除未分类，相应的文章也删除
				for (Article a : articleList) {
					commentMapper.delCommentByArticleId(a.getArticleId());// 把评论给删除
					articleMaper.delArticleById(a.getArticleId());// 删除文章
				}
			} else {// 删除已知分类，对应的文章移至未分类
				Category unkownCategory = categoryMapper.selectCategoryByName("未分类", userName);
				if (unkownCategory == null) {// 未分类为空，创建未分类
					unkownCategory = new Category();
					unkownCategory.setCategoryName("未分类");
					unkownCategory.setCategoryUserName(userName);
					unkownCategory.setCategoryDescription("用来保存未分类的文章");
					categoryMapper.insertCategory(unkownCategory);// 添加分类未分类
				}
				for (Article a : articleList) {// 移至未分类
					articleMaper.updateArticleCategoryById(a.getArticleId(), unkownCategory.getCategoryId());
				}
			}
		}

		return categoryMapper.delCategoryById(categoryId);
	}

	/**
	 * 添加分类
	 */
	@Override
	public int addCategory(String userName,String pCategoryName,String cCategoryName,String categoryDescription) {
		if(pCategoryName.equals("未分类")||cCategoryName.equals("未分类")){
			return 0;
		}
		if(pCategoryName.equals(cCategoryName)){
			return 0;
		}
		Category oldCCategory=categoryMapper.selectCategoryByName(cCategoryName, userName);
		if(oldCCategory!=null){//和子分类重复，直接返回0
			return 0;
		}
		Category oldPCategory=categoryMapper.selectCategoryByName(pCategoryName, userName);
		Category pCategory=null;
		if(oldPCategory==null){//不存在父分类，添加父分类
			pCategory=new Category();
			pCategory.setCategoryName(pCategoryName);
			if(cCategoryName.equals(""))
				pCategory.setCategoryDescription(categoryDescription);
			pCategory.setCategoryPid(0);
			pCategory.setCategoryStatus(1);
			pCategory.setCategoryUserName(userName);
			categoryMapper.insertCategory(pCategory);
		}
		if(!cCategoryName.equals("")){//子分类名称不为空，添加子分类
			Category cCategory=new Category();
			if(oldPCategory!=null){//如果存在父分类，设置子分类的父分类Id
				cCategory.setCategoryPid(oldPCategory.getCategoryId());
			}else{//如果不存在，设置子分类的父分类Id为新建父分类Id
				cCategory.setCategoryPid(pCategory.getCategoryId());
			}
			cCategory.setCategoryName(cCategoryName);
			cCategory.setCategoryDescription(categoryDescription);
			cCategory.setCategoryStatus(1);
			cCategory.setCategoryUserName(userName);
			categoryMapper.insertCategory(cCategory);
		}
		return 1;
	}
	/**
	 * 修改分类
	 */
	@Override
	public int updateCategory(String userName,Integer categoryId, String categoryName, String categoryDescription) {
		Category category=categoryMapper.selectCategoryByName(categoryName, userName);
		if(category!=null&&category.getCategoryId()!=categoryId)
			return 0;
		else
			return categoryMapper.updateByCategoryId(categoryId, categoryName, categoryDescription);
	}

}
