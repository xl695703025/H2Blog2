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
import com.yuxia.blog.service.TagService;

@Controller
public class TagController {

	@Autowired
	private TagService tagService;

	/**
	 * 获取所有的Tag
	 * 
	 */
	@RequestMapping(value = "getTagByUserName", method = RequestMethod.POST)
	@ResponseBody
	public Object getTagByUserName(HttpSession session) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		String userName = ((User) session.getAttribute("user")).getUserName();
		hashMap.put("tagList", tagService.getTagList(userName));
		return JSON.toJSONString(hashMap);
	}

	/**
	 * 根据id删除Tag
	 * 
	 */
	@RequestMapping(value = "delTag", method = RequestMethod.POST)
	@ResponseBody
	public Object delComment(Integer tagId) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		int res = tagService.delTagById(tagId);
		if (res > 0) {
			hashMap.put("res", true);
		} else {
			hashMap.put("res", false);
		}
		return JSON.toJSONString(hashMap);
	}
	/**
	 * 添加标签
	 */
	@RequestMapping(value = "addTag", method = RequestMethod.POST)
	@ResponseBody
	public Object addComment(HttpSession session, String tagName, String tagDescription) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		String userName = ((User) session.getAttribute("user")).getUserName();
		int res = tagService.addTag(userName, tagName, tagDescription);
		hashMap.put("res", res);
		return JSON.toJSONString(hashMap);
	}
	/**
	 * 修改标签
	 */
	@RequestMapping(value = "updateTag", method = RequestMethod.POST)
	@ResponseBody
	public Object addComment(Integer tagId,String tagName, String tagDescription) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		int res=tagService.updateTag(tagId, tagName, tagDescription);
		hashMap.put("res", res);
		return JSON.toJSONString(hashMap);
	}
}
