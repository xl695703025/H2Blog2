package com.yuxia.blog.entity;

public class Tag {

	private int tagId;//标签Id
	private String tagName;//标签名
	private String tagUserName;//标签用户Id
	private String tagDescription;//标签描述
	private int tagStatus;//标签状态
	public int getTagId() {
		return tagId;
	}
	public void setTagId(int tagId) {
		this.tagId = tagId;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getTagUserName() {
		return tagUserName;
	}
	public void setTagUserName(String tagUserName) {
		this.tagUserName = tagUserName;
	}
	public String getTagDescription() {
		return tagDescription;
	}
	public void setTagDescription(String tagDescription) {
		this.tagDescription = tagDescription;
	}
	public int getTagStatus() {
		return tagStatus;
	}
	public void setTagStatus(int tagStatus) {
		this.tagStatus = tagStatus;
	}
	@Override
	public String toString() {
		return "Tag [tagId=" + tagId + ", tagName=" + tagName + ", tagUserName=" + tagUserName + ", tagDescription="
				+ tagDescription + ", tagStatus=" + tagStatus + "]";
	}

}
