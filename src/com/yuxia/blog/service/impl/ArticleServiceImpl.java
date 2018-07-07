package com.yuxia.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuxia.blog.entity.Article;
import com.yuxia.blog.entity.Tag;
import com.yuxia.blog.mapper.ArticleMapper;
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

	@Override
	public Integer getArticleCount(String userName) {
		return articleMapper.selectArticleCount(userName);
	}

	@Override
	public Integer getArticleCommentCount(String userName) {
		return articleMapper.selectArticleCommentCount(userName);
	}

	@Override
	public Integer getArticleViewCount(String userName) {
		return articleMapper.selectArticleViewCount(userName);
	}

	@Override
	public Integer getArticleLikeCount(String userName) {
		return articleMapper.selectArticleLikeCount(userName);
	}

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

	@Override
	public Integer delArticle(Integer articleId) {
		commentMapper.delCommentByArticleId(articleId);// 先把评论给删除
		return articleMapper.delArticleById(articleId);
	}

}
