package com.yuxia.blog.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuxia.blog.entity.Comment;

public interface CommentService {

	public List<Comment> getComment(String userName);

	public int delComment(Integer commentId);

	public List<Comment> getCommentByArticleId(Integer articleId);

	public int addComment(Integer articleId, String commentAuthorName, String commentAuthorEmail, String commentContent,
			String commenIp, Date commentCreateTime);
}
