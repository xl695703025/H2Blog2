package com.yuxia.blog.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.yuxia.blog.entity.Category;
import com.yuxia.blog.entity.User;
import com.yuxia.blog.service.CategoryService;
import com.yuxia.blog.service.UserService;

/**
 * 用户的Controller
 * 
 * @author YuXia
 * @date 2018年6月29日
 */
@Controller
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	private CategoryService categoryService;
	/**
	 * 登录页面
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	/**
	 * 用户登录
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public Object login(@RequestParam("userName") String userName, @RequestParam("password") String password,
			HttpSession session, HttpServletRequest request) {
		User user = userService.login(userName, password);
		Map<String, String> hashMap = new HashMap<String, String>();
		if (null != user) {// 登录成功,跳转到后台主页
			session.setAttribute("user", user);
			hashMap.put("result", "success");
		} else {// 用户名或者密码错误
			hashMap.put("result", "error");
			hashMap.put("error", "用户名或密码错误");
		}
		return JSON.toJSONString(hashMap);
	}

	/**
	 * 注册新用户
	 */
	@RequestMapping(value = "register", method = RequestMethod.POST)
	@ResponseBody
	public Object register(User user, HttpSession session, HttpServletRequest request,
			@RequestParam("avatar") MultipartFile avatar) {
		String avatarPath = null;
		Map<String, Object> hashMap = new HashMap<String, Object>();
		if (!avatar.isEmpty()) {
			String path = request.getSession().getServletContext()
					.getRealPath("statics" + File.separator + "images");
			// 获取原文件名
			String oldFile = avatar.getOriginalFilename();
			// 获取原文件后缀
			String suffix = FilenameUtils.getExtension(oldFile);
			// 设置文件大小最大值
			int fileSize = 5000000;
			if (avatar.getSize() > fileSize) {
				hashMap.put("result", "error");
				hashMap.put("error", "上传文件不得超过5MB");
			} else if (suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("png")
					|| suffix.equalsIgnoreCase("jpeg") || suffix.equalsIgnoreCase("jpeg")) {
				// 生成随机的头像名
				String fileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "avatar.jpg";
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				try {
					avatar.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
					hashMap.put("result", "error");
					hashMap.put("error", "上传失败");
					return JSON.toJSONString(hashMap);
				}
				avatarPath = "statics" + File.separator + "images" + File.separator + fileName;
			} else {
				hashMap.put("result", "error");
				hashMap.put("error", "图片格式不对");
				return JSON.toJSONString(hashMap);
			}
			user.setUserAvatar(avatarPath);
		}
		user.setUserRegisterTime(new Date());
		user.setUserStatus(1);
		userService.register(user);
		hashMap.put("result", "success");
		return JSON.toJSONString(hashMap);
	}

	/**
	 * 检测用户名是否存在
	 */
	@RequestMapping(value = "checkUserName", method = RequestMethod.POST)
	@ResponseBody
	public Object checkUserName(@RequestParam("userName") String userName) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		if (userService.checkUserName(userName)) {
			hashMap.put("result", "success");
			hashMap.put("message", "用户名可用");
		} else {
			hashMap.put("result", "fail");
			hashMap.put("message", "用户名已存在");
		}
		return JSON.toJSONString(hashMap);
	}

	/**
	 * 登录成功页面
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(HttpSession session) {
		String userName=((User)session.getAttribute("user")).getUserName();
		List<Category> categoryList = categoryService.getCategoryList(userName);
		session.setAttribute("categoryList", categoryList);
		return "index";
	}

	/**
	 * 注销登录
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:login";
	}
}
