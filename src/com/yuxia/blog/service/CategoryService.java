package com.yuxia.blog.service;

import java.util.List;

import com.yuxia.blog.entity.Category;

public interface CategoryService {

	public List<Category> getCategoryList(String userName);
}
