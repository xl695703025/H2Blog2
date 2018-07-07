package com.yuxia.blog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yuxia.blog.entity.User;
import com.yuxia.blog.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	/**
	 * 获取所有分类
	 */
	@RequestMapping(value = "getCategory", method = RequestMethod.POST)
	@ResponseBody
	public Object getCategory(HttpSession session) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		String userName = ((User) session.getAttribute("user")).getUserName();
		hashMap.put("categoryList", categoryService.getCategoryList(userName));
		return JSON.toJSONString(hashMap);
	}

	/**
	 * 根据id删除分类
	 */
	@RequestMapping(value = "delCategory", method = RequestMethod.POST)
	@ResponseBody
	public Object delCategory(Integer categoryId, HttpSession session) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		String userName = ((User) session.getAttribute("user")).getUserName();
		int res = categoryService.delCategoryById(categoryId, userName);
		hashMap.put("res", res);
		return JSON.toJSONString(hashMap);
	}

	/**
	 * 添加分类
	 */
	@RequestMapping(value = "addCategory", method = RequestMethod.POST)
	@ResponseBody
	public Object addCategory(String pCategoryName, String cCategoryName, String categoryDescription,
			HttpSession session) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		String userName = ((User) session.getAttribute("user")).getUserName();
		int res=categoryService.addCategory(userName, pCategoryName, cCategoryName, categoryDescription);
		hashMap.put("res", res);
		return JSON.toJSONString(hashMap);
	}
	/**
	 * 修改分类
	 */
	@RequestMapping(value = "updateCategory", method = RequestMethod.POST)
	@ResponseBody
	public Object updateCategory(Integer categoryId, String categoryName, String categoryDescription,
			HttpSession session) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		String userName = ((User) session.getAttribute("user")).getUserName();
		int res=categoryService.updateCategory(userName, categoryId, categoryName, categoryDescription);
		hashMap.put("res", res);
		return JSON.toJSONString(hashMap);
	}
}
