package com.yuxia.blog.entity;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class Comment {
	private int commentId;//评论Id
	//private int commentPid;//父评论Id
	private List<Comment> childComment;//字评论
	private String commentPname;//父评论人名称
	private String commentArticleTitle;//文章标题
	private String commentAuthorName;//评论人名称
	private String commentAuthorEmail;//评论人邮箱
	private String commentContent;//评论内容
	private String commentIp;//评论ip
	@JSONField(format="yyyy-MM-dd HH:dd:ss")
	private Date commentCreateTime;//评论时间
	private String commentStatus;//评论状态
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public List<Comment> getChildComment() {
		return childComment;
	}
	public void setChildComment(List<Comment> childComment) {
		this.childComment = childComment;
	}
	public String getCommentPname() {
		return commentPname;
	}
	public void setCommentPname(String commentPname) {
		this.commentPname = commentPname;
	}
	public String getCommentArticleTitle() {
		return commentArticleTitle;
	}
	public void setCommentArticleTitle(String commentArticleTitle) {
		this.commentArticleTitle = commentArticleTitle;
	}
	public String getCommentAuthorName() {
		return commentAuthorName;
	}
	public void setCommentAuthorName(String commentAuthorName) {
		this.commentAuthorName = commentAuthorName;
	}
	public String getCommentAuthorEmail() {
		return commentAuthorEmail;
	}
	public void setCommentAuthorEmail(String commentAuthorEmail) {
		this.commentAuthorEmail = commentAuthorEmail;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentIp() {
		return commentIp;
	}
	public void setCommentIp(String commentIp) {
		this.commentIp = commentIp;
	}
	public Date getCommentCreateTime() {
		return commentCreateTime;
	}
	public void setCommentCreateTime(Date commentCreateTime) {
		this.commentCreateTime = commentCreateTime;
	}
	public String getCommentStatus() {
		return commentStatus;
	}
	public void setCommentStatus(String commentStatus) {
		this.commentStatus = commentStatus;
	}
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", childComment=" + childComment + ", commentPname=" + commentPname
				+ ", commentArticleTitle=" + commentArticleTitle + ", commentAuthorName=" + commentAuthorName
				+ ", commentAuthorEmail=" + commentAuthorEmail + ", commentContent=" + commentContent + ", commentIp="
				+ commentIp + ", commentCreateTime=" + commentCreateTime + ", commentStatus=" + commentStatus + "]";
	}


}
