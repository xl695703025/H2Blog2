package com.yuxia.blog.service;

import java.util.List;

import com.yuxia.blog.entity.Category;

public interface CategoryService {

	public List<Category> getCategoryList(String userName);
	public int delCategoryById(Integer  categoryId,String userName);
	public int addCategory(String userName,String pCategoryName,String cCategoryName,String categoryDescription);
	public int updateCategory(String userName,Integer categoryId,String categoryName,String categoryDescription);
}
