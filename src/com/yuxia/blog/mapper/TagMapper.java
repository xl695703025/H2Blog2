package com.yuxia.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuxia.blog.entity.Tag;

public interface TagMapper {

	public List<Tag> selectTagUserName(@Param("userName")String userName);
}
