package com.yuxia.blog.service;

import java.util.List;

import com.yuxia.blog.entity.Comment;

public interface CommentService {

	public List<Comment> getComment(String userName);
	public int delComment(Integer commentId);
}
