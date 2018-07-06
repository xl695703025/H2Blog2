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
	
	@RequestMapping(value="getCategory",method=RequestMethod.POST)
	@ResponseBody
	public Object getCategory(HttpSession session){
		Map< String, Object> hashMap=new HashMap<String, Object>();
		String userName=((User)session.getAttribute("user")).getUserName();
		hashMap.put("categoryList", categoryService.getCategoryList(userName));
		return JSON.toJSONString(hashMap);
	}
}
