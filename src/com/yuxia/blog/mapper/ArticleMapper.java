package com.yuxia.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuxia.blog.entity.Article;

public interface ArticleMapper {

	public List<Article> selectArticleByUserName(@Param("userName") String userName, @Param("index") Integer index,
			@Param("pageSize") Integer pageSize);

	public List<Article> selectArticleByCategoryId(@Param("userName") String userName, @Param("index") Integer index,
			@Param("pageSize") Integer pageSize, @Param("categoryId") Integer categoryId);

	public Integer selectArticleCount(@Param("userName") String userName);

	public Integer selectArticleViewCount(@Param("userName") String userName);

	public Integer selectArticleLikeCount(@Param("userName") String userName);

	public Integer selectArticleCommentCount(@Param("userName") String userName);
}
