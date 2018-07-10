package com.yuxia.blog.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuxia.blog.entity.Comment;
import com.yuxia.blog.mapper.ArticleMapper;
import com.yuxia.blog.mapper.CommentMapper;
import com.yuxia.blog.service.CommentService;
@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private ArticleMapper articleMapper;
	@Override
	public List<Comment> getComment(String userName) {
		return commentMapper.selectCommentByUserName(userName);
	}
	@Override
	public int delComment(Integer commentId) {
		return commentMapper.delCommentById(commentId);
	}
	@Override
	public List<Comment> getCommentByArticleId(Integer articleId) {
		articleMapper.updateArticleInfo(articleId, 1, null, null);
		return commentMapper.selectCommentArticleId(articleId);
	}
	@Override
	public int addComment(Integer articleId, String commentAuthorName, String commentAuthorEmail, String commentContent,
			String commenIp, Date commentCreateTime) {
		articleMapper.updateArticleInfo(articleId, null, 1, null);
		return commentMapper.insertComment(articleId, commentAuthorName, commentAuthorEmail, commentContent, commenIp, commentCreateTime);
	}

}
