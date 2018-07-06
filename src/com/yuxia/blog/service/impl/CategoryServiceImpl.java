package com.yuxia.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuxia.blog.entity.Category;
import com.yuxia.blog.mapper.CategoryMapper;
import com.yuxia.blog.service.CategoryService;
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;
	@Override
	public List<Category> getCategoryList(String userName) {
		return categoryMapper.selectCategoryByUserName(userName);
	}

}
