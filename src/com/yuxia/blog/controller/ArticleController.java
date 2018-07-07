package com.yuxia.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yuxia.blog.entity.Article;
import com.yuxia.blog.entity.User;
import com.yuxia.blog.other.Page;
import com.yuxia.blog.service.ArticleService;

@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	/**
	 * 获取Bolg总博文，访问量，点赞数和总留言等信息
	 */
	@RequestMapping(value="getBlogInfo",method=RequestMethod.POST)
	@ResponseBody
	public Object getBlogInfo(HttpSession session){
		Map<String , Object> hashMap=new HashMap<String, Object>();
		User user=(User) session.getAttribute("user");//获取当前用户
		Integer articleCount=articleService.getArticleCount(user.getUserName());
		hashMap.put("articleCount",articleCount );
		
		Integer likeCount=articleService.getArticleLikeCount(user.getUserName());
		if(likeCount==null)//数据库用sum()计算的，可能为空，文章数是用count()计数，不会为空
			likeCount=0;
		hashMap.put("likeCount",likeCount );
		Integer viewCount=articleService.getArticleViewCount(user.getUserName());
		if(viewCount==null)
			viewCount=0;
		hashMap.put("viewCount", viewCount);
		Integer commentCount=articleService.getArticleCommentCount(user.getUserName());
		if(commentCount==null)
			commentCount=0;
		hashMap.put("commentCount",commentCount);
		return JSON.toJSONString(hashMap);
	}
	
	/**
	 * 获取文章列表
	 */
	@RequestMapping(value="getArticles",method=RequestMethod.POST)
	@ResponseBody
	public Object getArticles(HttpSession session,Page page){
		Map<String , Object> hashMap=new HashMap<String, Object>();
		String userName=((User)session.getAttribute("user")).getUserName();//获取当前用户名
		List<Article> articleList=articleService.getArticle(userName, page);//获取文章列表
		hashMap.put("articleList", articleList);//将文章列表加入到hashMap中
		return JSON.toJSONString(hashMap);
	}
	/**
	 * 根据分类获取文章列表
	 * @return
	 */
	@RequestMapping(value="getArticlesByCategory",method=RequestMethod.POST)
	@ResponseBody
	public Object getArticlesByCategory(HttpSession session,Page page,Integer id){
		Map<String, Object> hashMap=new HashMap<String, Object>();
		String userName=((User)session.getAttribute("user")).getUserName();//获取当前用户名
		List<Article> articleList=articleService.getArticleListByCategoryId(userName, page, id);
		hashMap.put("articleList", articleList);
		return JSON.toJSONString(hashMap);
	}
	/**
	 * 根据文章Id删除文章
	 */
	@RequestMapping(value="delArticle",method=RequestMethod.POST)
	@ResponseBody
	public Object delArticle(Integer articleId){
		Map<String, Object> hashMap=new HashMap<String, Object>();
		int res=articleService.delArticle(articleId);
		hashMap.put("res", res);
		return JSON.toJSONString(hashMap);
	}
}
