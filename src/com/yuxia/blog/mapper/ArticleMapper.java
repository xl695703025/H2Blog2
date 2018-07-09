package com.yuxia.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuxia.blog.entity.Article;

public interface ArticleMapper {

	public List<Article> selectArticleByUserName(@Param("userName") String userName, @Param("index") Integer index,
			@Param("pageSize") Integer pageSize);

	public List<Article> selectArticleByCategoryId(@Param("userName") String userName, @Param("index") Integer index,
			@Param("pageSize") Integer pageSize, @Param("categoryId") Integer categoryId);
	
	public Article selectArticleById(Integer articleId);

	public Integer selectArticleCount(@Param("userName") String userName);

	public Integer selectArticleViewCount(@Param("userName") String userName);

	public Integer selectArticleLikeCount(@Param("userName") String userName);

	public Integer selectArticleCommentCount(@Param("userName") String userName);

	public Integer delArticleById(@Param("articleId") Integer articleId);

	public Integer updateArticleCategoryById(@Param("articleId") Integer articleId,
			@Param("categoryId") Integer categoryId);

	public Integer insertArticle(@Param("article") Article article,
			@Param("userName")String userName,
			@Param("articleParentCategoryId") Integer articleParentCategoryId,
			@Param("articleChildCategoryId") Integer articleChildCategoryId);
	public Integer updateArticle(@Param("article") Article article,
			@Param("articleParentCategoryId") Integer articleParentCategoryId,
			@Param("articleChildCategoryId") Integer articleChildCategoryId);
	public Integer getTotalPage(@Param("categoryId")Integer id,@Param("userName")String userName);
}
