package com.yuxia.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuxia.blog.entity.Article;
import com.yuxia.blog.mapper.ArticleMapper;
import com.yuxia.blog.other.Page;
import com.yuxia.blog.service.ArticleService;
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleMapper articleMapper;
	@Override
	public List<Article> getArticle(String userName, Page page) {
		int index=(page.getPageNow()-1)*page.getPageSize();
		int pageSize=page.getPageSize();
		System.out.println(index+"####"+pageSize);
		return articleMapper.selectArticleByUserName(userName, index,pageSize);
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
		int index=(page.getPageNow()-1)*page.getPageSize();
		int pageSize=page.getPageSize();
		return articleMapper.selectArticleByCategoryId(userName, index,pageSize,CategoryId);
	}

}
