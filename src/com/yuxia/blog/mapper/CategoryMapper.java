package com.yuxia.blog.mapper;

import java.util.List;

import com.yuxia.blog.entity.Category;

public interface CategoryMapper {

	public List<Category> selectCategoryByUserName(String userName);
}
