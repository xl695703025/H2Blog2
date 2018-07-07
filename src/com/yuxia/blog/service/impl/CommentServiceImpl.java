package com.yuxia.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuxia.blog.entity.Comment;
import com.yuxia.blog.mapper.CommentMapper;
import com.yuxia.blog.service.CommentService;
@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper commentMapper;
	@Override
	public List<Comment> getComment(String userName) {
		return commentMapper.selectCommentByUserName(userName);
	}
	@Override
	public int delComment(Integer commentId) {
		return commentMapper.delCommentById(commentId);
	}

}
