package com.yuxia.blog.entity;

import java.util.List;

public class Category {
	private int categoryId;//文章分类Id
	private String categoryUserName;//分类用户Id
	private int categoryPid;//文章父分类Id
	private List<Category> childCategoryList;//子分类
	private String categoryName;//分类名称
	private String categoryDescription;//分类描述
	private int categoryStatus;//分类状态
	public int getCategoryPid() {
		return categoryPid;
	}
	public void setCategoryPid(int categoryPid) {
		this.categoryPid = categoryPid;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public List<Category> getChildCategoryList() {
		return childCategoryList;
	}
	public void setChildCategoryList(List<Category> childCategoryList) {
		this.childCategoryList = childCategoryList;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	public int getCategoryStatus() {
		return categoryStatus;
	}
	public void setCategoryStatus(int categoryStatus) {
		this.categoryStatus = categoryStatus;
	}
	public String getCategoryUserName() {
		return categoryUserName;
	}
	public void setCategoryUserName(String categoryUserName) {
		this.categoryUserName = categoryUserName;
	}
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryUserName=" + categoryUserName + ", childCategoryList="
				+ childCategoryList + ", categoryName=" + categoryName + ", categoryDescription=" + categoryDescription
				+ ", categoryStatus=" + categoryStatus + "]";
	}

}
