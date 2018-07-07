package com.yuxia.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuxia.blog.entity.Category;

public interface CategoryMapper {

	public List<Category> selectCategoryByUserName(@Param("userName")String userName);
	public int delCategoryById(@Param("categoryId")Integer categoryId);
	public int insertCategory(Category category);
	public Category selectCategoryByName(@Param("categoryName")String categoryName,@Param("userName")String userName);
	public Category selectCategoryById(@Param("categoryId")Integer categoryId);
	public int updateByCategoryId(@Param("categoryId")Integer categoryId,@Param("categoryName")String categoryName,@Param("categoryDescription")String categoryDescription);
}
