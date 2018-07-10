package com.yuxia.blog.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	@RequestMapping(value = "getBlogInfo", method = RequestMethod.POST)
	@ResponseBody
	public Object getBlogInfo(HttpSession session) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		User user = (User) session.getAttribute("user");// 获取当前用户
		Integer articleCount = articleService.getArticleCount(user.getUserName());
		hashMap.put("articleCount", articleCount);

		Integer likeCount = articleService.getArticleLikeCount(user.getUserName());
		if (likeCount == null)// 数据库用sum()计算的，可能为空，文章数是用count()计数，不会为空
			likeCount = 0;
		hashMap.put("likeCount", likeCount);
		Integer viewCount = articleService.getArticleViewCount(user.getUserName());
		if (viewCount == null)
			viewCount = 0;
		hashMap.put("viewCount", viewCount);
		Integer commentCount = articleService.getArticleCommentCount(user.getUserName());
		if (commentCount == null)
			commentCount = 0;
		hashMap.put("commentCount", commentCount);
		return JSON.toJSONString(hashMap);
	}

	/**
	 * 获取文章列表
	 */
	@RequestMapping(value = "getArticles", method = RequestMethod.POST)
	@ResponseBody
	public Object getArticles(HttpSession session, Page page) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		String userName = ((User) session.getAttribute("user")).getUserName();// 获取当前用户名
		List<Article> articleList = articleService.getArticle(userName, page);// 获取文章列表
		hashMap.put("articleList", articleList);// 将文章列表加入到hashMap中
		return JSON.toJSONString(hashMap);
	}

	/**
	 * 根据分类获取文章列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "getArticlesByCategory", method = RequestMethod.POST)
	@ResponseBody
	public Object getArticlesByCategory(HttpSession session, Page page, Integer id) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		String userName = ((User) session.getAttribute("user")).getUserName();// 获取当前用户名
		List<Article> articleList = articleService.getArticleListByCategoryId(userName, page, id);
		hashMap.put("articleList", articleList);
		return JSON.toJSONString(hashMap);
	}
	/**
	 * 根据Id获取文章
	 * @return
	 */
	@RequestMapping(value = "getArticleById", method = RequestMethod.POST)
	@ResponseBody
	public Object getArticlesByCategory(Integer id) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		Article article=articleService.getArticleById(id);
		hashMap.put("article", article);
		return JSON.toJSONString(hashMap);
	}
	/**
	 * 根据文章Id删除文章
	 */
	@RequestMapping(value = "delArticle", method = RequestMethod.POST)
	@ResponseBody
	public Object delArticle(Integer articleId) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		int res = articleService.delArticle(articleId);
		hashMap.put("res", res);
		return JSON.toJSONString(hashMap);
	}
	/**
	 * 上传文章图片
	 */
	@RequestMapping(value = "uploadImg", method = RequestMethod.POST)
	@ResponseBody
	public Object uploadImg(HttpServletRequest request, @RequestParam("img") MultipartFile img) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		List<String> imgFile = new ArrayList<String>();
		// 获取路径
		String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadImg");
		// 随机生成文件名
		String fileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + ".jpg";
		// 生成目标文件
		File targetFile = new File(path, fileName);
		// 上传图片
		try {
			img.transferTo(targetFile);
			imgFile.add("http://localhost:8080/H2Blog/statics/uploadImg/" + fileName);
			hashMap.put("data", imgFile);
			hashMap.put("errno", 0);
		} catch (IOException e) {
			hashMap.put("errno", 1);
			e.printStackTrace();
		}
		System.out.println(JSON.toJSONString(hashMap));
		return JSON.toJSONString(hashMap);
	}
	/**
	 * 添加文章
	 */
	@RequestMapping(value = "addArticle", method = RequestMethod.POST)
	@ResponseBody
	public Object addArticle(HttpSession session, Article article,
			String pCategory, String cCategory,String tags) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		String userName = ((User) session.getAttribute("user")).getUserName();
		int res=articleService.addArticle(article, userName, pCategory, cCategory);
		hashMap.put("res", res);
		return JSON.toJSONString(hashMap);
	}
	/**
	 * 修改文章
	 */
	@RequestMapping(value = "updateArticle", method = RequestMethod.POST)
	@ResponseBody
	public Object updateArticle(HttpSession session, Article article,
			String pCategory, String cCategory,String tags) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		String userName = ((User) session.getAttribute("user")).getUserName();
		int res=articleService.updateArticle(article, userName, pCategory, cCategory);
		hashMap.put("res", res);
		return JSON.toJSONString(hashMap);
	}
	/**
	 * 获取分页
	 */
	@RequestMapping(value = "getPage", method = RequestMethod.POST)
	@ResponseBody
	public Object getPage(HttpSession session, Integer id) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		String userName = ((User) session.getAttribute("user")).getUserName();
		int res=articleService.getTotalPage(id, userName);
		hashMap.put("res", res%10==0?res/10:res/10+1);
		return JSON.toJSONString(hashMap);
	}
	/**
	 * 点赞
	 */
	@RequestMapping(value = "like", method = RequestMethod.POST)
	@ResponseBody
	public Object like(HttpServletResponse response,HttpServletRequest request,Integer articleId) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		Cookie[] cookies = request.getCookies();
		for(Cookie c:cookies){
			if(c.getName().equals("article"+articleId)){
				hashMap.put("res", 0);
				return JSON.toJSONString(hashMap);
			}
		}
		int res=articleService.like(articleId);
		Cookie cookie=new Cookie("article"+articleId, "ture");
		cookie.setMaxAge(Integer.MAX_VALUE);
		response.addCookie(cookie);
		hashMap.put("res", res);
		return JSON.toJSONString(hashMap);
	}
}
