package com.yuxia.blog.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuxia.blog.entity.Article;
import com.yuxia.blog.entity.Category;
import com.yuxia.blog.entity.Tag;
import com.yuxia.blog.mapper.ArticleMapper;
import com.yuxia.blog.mapper.CategoryMapper;
import com.yuxia.blog.mapper.CommentMapper;
import com.yuxia.blog.mapper.TagMapper;
import com.yuxia.blog.other.Page;
import com.yuxia.blog.service.ArticleService;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private TagMapper tagMapper;
	@Autowired
	private CategoryMapper categoryMapper;
	/**
	 * 根据用户名获取文章列表
	 */
	@Override
	public List<Article> getArticle(String userName, Page page) {
		int index = (page.getPageNow() - 1) * page.getPageSize();
		int pageSize = page.getPageSize();
		List<Article> articleList = articleMapper.selectArticleByUserName(userName, index, pageSize);
		for (Article a : articleList) {
			if (a.getArticleTagIds() == null) {
				continue;
			}
			String[] tagList = a.getArticleTagIds().split(",");
			StringBuffer tagsName = new StringBuffer();
			for (String tagId : tagList) {
				if (tagId.equals("") || tagId == null)
					continue;
				Tag tag = tagMapper.selectTagByTagId(Integer.parseInt(tagId));// 根据Id获取这个Tag
				tagsName.append(tag.getTagName() + ",");
			}
			if (tagsName.lastIndexOf(",") != -1 && tagsName.lastIndexOf(",") == tagsName.length() - 1) {
				tagsName.deleteCharAt(tagsName.length() - 1);
			}
			a.setArticleTagIds(tagsName.toString());
		}
		return articleList;
	}
	/**
	 * 获取文章数
	 */
	@Override
	public Integer getArticleCount(String userName) {
		return articleMapper.selectArticleCount(userName);
	}
	/**
	 * 评论总数
	 */
	@Override
	public Integer getArticleCommentCount(String userName) {
		return articleMapper.selectArticleCommentCount(userName);
	}
	/**
	 * 访问量
	 */
	@Override
	public Integer getArticleViewCount(String userName) {
		return articleMapper.selectArticleViewCount(userName);
	}
	/**
	 * 点赞数
	 */
	@Override
	public Integer getArticleLikeCount(String userName) {
		return articleMapper.selectArticleLikeCount(userName);
	}
	/**
	 * 根据分类Id获取文章
	 */
	@Override
	public List<Article> getArticleListByCategoryId(String userName, Page page, Integer CategoryId) {
		int index = (page.getPageNow() - 1) * page.getPageSize();
		int pageSize = page.getPageSize();
		List<Article> articleList = articleMapper.selectArticleByCategoryId(userName, index, pageSize, CategoryId);
		for (Article a : articleList) {
			if (a.getArticleTagIds() == null) {
				continue;
			}
			String[] tagList = a.getArticleTagIds().split(",");
			StringBuffer tagsName = new StringBuffer();
			for (String tagId : tagList) {
				if (tagId.equals("") || tagId == null)
					continue;
				Tag tag = tagMapper.selectTagByTagId(Integer.parseInt(tagId));// 根据Id获取这个Tag
				tagsName.append(tag.getTagName() + ",");
			}
			if (tagsName.lastIndexOf(",") != -1 && tagsName.lastIndexOf(",") == tagsName.length() - 1) {
				tagsName.deleteCharAt(tagsName.length() - 1);
			}
			a.setArticleTagIds(tagsName.toString());
		}
		return articleList;
	}
	/**
	 * 删除文章
	 */
	@Override
	public Integer delArticle(Integer articleId) {
		commentMapper.delCommentByArticleId(articleId);// 先把评论给删除
		return articleMapper.delArticleById(articleId);
	}
	/**
	 * 添加文章
	 */
	@Override
	public Integer addArticle(Article article, String userName,String pCategoryName, String cCategoryName) {
		if(pCategoryName.equals(cCategoryName)){
			return 0;
		}
		Category pCategory=categoryMapper.selectCategoryByName(pCategoryName, userName);
		Category cCategory=categoryMapper.selectCategoryByName(cCategoryName, userName);
		if(pCategory==null && cCategory==null){
			pCategory=new Category();
			pCategory.setCategoryDescription("");
			pCategory.setCategoryName(pCategoryName);
			pCategory.setCategoryPid(0);
			pCategory.setCategoryUserName(userName);
			categoryMapper.insertCategory(pCategory);
		}else if(pCategory==null && cCategory!=null){
			return 0;
		}
		Integer articleParentCategoryId= pCategory.getCategoryId();
		
		if(cCategory==null){
			cCategory=new Category();
			cCategory.setCategoryDescription("");
			cCategory.setCategoryName(cCategoryName);
			cCategory.setCategoryPid(articleParentCategoryId);
			cCategory.setCategoryUserName(userName);
			categoryMapper.insertCategory(cCategory);
		}
		Integer articleChildCategoryId=cCategory.getCategoryId();
		article.setArticlePostTime(new Date());
		article.setArticleStatus(1);
		StringBuffer ids=new StringBuffer();
		for(String tagName:article.getArticleTagIds().split(",|，")){
			Tag tag=tagMapper.selectTagByTagName(userName, tagName);
			if(tag!=null){
				ids.append(tag.getTagId()+",");
			}else{
				tag=new Tag();
				tag.setTagDescription("");
				tag.setTagName(tagName);
				tag.setTagUserName(userName);
				tagMapper.insertTag(tag);
				ids.append(tag.getTagId()+",");
			}
		}
		if (ids.lastIndexOf(",") != -1 && ids.lastIndexOf(",") == ids.length() - 1) {
			ids.deleteCharAt(ids.length() - 1);
		}
		article.setArticleTagIds(ids.toString());
		return articleMapper.insertArticle(article, userName, articleParentCategoryId, articleChildCategoryId);
	}
	@Override
	public Article getArticleById(Integer articleId) {
		return articleMapper.selectArticleById(articleId);
	}
	/**
	 * 修改文章
	 */
	@Override
	public Integer updateArticle(Article article, String userName,String pCategoryName, String cCategoryName){
		System.out.println(1);
		if(pCategoryName.equals(cCategoryName)){
			return 0;
		}
		System.out.println(1);
		Category pCategory=categoryMapper.selectCategoryByName(pCategoryName, userName);
		Category cCategory=categoryMapper.selectCategoryByName(cCategoryName, userName);
		if(pCategory==null && cCategory==null){
			pCategory=new Category();
			pCategory.setCategoryDescription("");
			pCategory.setCategoryName(pCategoryName);
			pCategory.setCategoryPid(0);
			pCategory.setCategoryUserName(userName);
			categoryMapper.insertCategory(pCategory);
		}else if(pCategory==null && cCategory!=null){
			return 0;
		}
		Integer articleParentCategoryId= pCategory.getCategoryId();

		System.out.println(1);
		if(cCategory==null){
			cCategory=new Category();
			cCategory.setCategoryDescription("");
			cCategory.setCategoryName(cCategoryName);
			cCategory.setCategoryPid(articleParentCategoryId);
			cCategory.setCategoryUserName(userName);
			categoryMapper.insertCategory(cCategory);
		}
		Integer articleChildCategoryId=cCategory.getCategoryId();
		article.setArticlePostTime(new Date());
		article.setArticleStatus(1);
		StringBuffer ids=new StringBuffer();
		for(String tagName:article.getArticleTagIds().split(",|，")){
			Tag tag=tagMapper.selectTagByTagName(userName, tagName);
			if(tag!=null){
				ids.append(tag.getTagId()+",");
			}else{
				tag=new Tag();
				tag.setTagDescription("");
				tag.setTagName(tagName);
				tag.setTagUserName(userName);
				tagMapper.insertTag(tag);
				ids.append(tag.getTagId()+",");
			}
		}
		if (ids.lastIndexOf(",") != -1 && ids.lastIndexOf(",") == ids.length() - 1) {
			ids.deleteCharAt(ids.length() - 1);
		}
		article.setArticleTagIds(ids.toString());
		article.setArticleUpdateTime(new Date());
		return articleMapper.updateArticle(article, articleParentCategoryId, articleChildCategoryId);
	}
}
