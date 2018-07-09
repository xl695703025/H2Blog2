package com.yuxia.blog.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuxia.blog.entity.Article;
import com.yuxia.blog.other.Page;

public interface ArticleService {

	public List<Article> getArticle(String userName, Page page);

	public List<Article> getArticleListByCategoryId(String userName, Page page, Integer CategoryId);

	public Integer getArticleCount(String userName);

	public Integer getArticleViewCount(String userName);

	public Integer getArticleLikeCount(String userName);

	public Integer getArticleCommentCount(String userName);

	public Integer delArticle(Integer articleId);

	public Integer addArticle(Article article, String userName,String pCategoryName, String cCategoryName);
	
	public Article getArticleById(Integer articleId);
	
	public Integer updateArticle(Article article, String userName,String pCategoryName, String cCategoryName);
	
	public Integer getTotalPage(Integer id,String userName);
}
