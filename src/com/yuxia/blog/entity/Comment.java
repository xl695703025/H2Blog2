package com.yuxia.blog.entity;

import java.util.List;

public class Comment {
	private int commentId;//评论Id
	//private int commentPid;//父评论Id
	private List<Comment> childComment;//字评论
	private String commentPname;//父评论人名称
	private int commentArticleId;//文章Id
	private String commentAuthorName;//评论人名称
	private String commentAuthorEmail;//评论人邮箱
	private String commentContent;//评论内容
	private String commentIp;//评论ip
	private String commentCreateTime;//评论时间
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
	public int getCommentArticleId() {
		return commentArticleId;
	}
	public void setCommentArticleId(int commentArticleId) {
		this.commentArticleId = commentArticleId;
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
	public String getCommentCreateTime() {
		return commentCreateTime;
	}
	public void setCommentCreateTime(String commentCreateTime) {
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
		return "Comment [commentId=" + commentId + ", childComment="
				+ childComment + ", commentPname=" + commentPname
				+ ", commentArticleId=" + commentArticleId
				+ ", commentAuthorName=" + commentAuthorName
				+ ", commentAuthorEmail=" + commentAuthorEmail
				+ ", commentContent=" + commentContent + ", commentIp="
				+ commentIp + ", commentCreateTime=" + commentCreateTime
				+ ", commentStatus=" + commentStatus + "]";
	}
}
