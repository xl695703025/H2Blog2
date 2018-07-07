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
import com.yuxia.blog.service.CommentService;

@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;
	/**
	 * 获取留言列表
	 * @param session
	 * @return
	 */
	@RequestMapping(value="getCommentList",method=RequestMethod.POST)
	@ResponseBody
	public Object getCommentList(HttpSession session){
		Map< String, Object> hashMap=new HashMap<String, Object>();
		String userName=((User)session.getAttribute("user")).getUserName();
		hashMap.put("commentList", commentService.getComment(userName));
		return JSON.toJSONString(hashMap);
	}
	/**
	 * 删除留言
	 * @param commentId
	 * @return
	 */
	@RequestMapping(value="delComment",method=RequestMethod.POST)
	@ResponseBody
	public Object delComment(Integer commentId){
		Map< String, Object> hashMap=new HashMap<String, Object>();
		int res=commentService.delComment(commentId);
		if(res>0){
			hashMap.put("res",true);
		}else{
			hashMap.put("res",false);
		}
		return JSON.toJSONString(hashMap);
	}
}
