package com.yuxia.blog.mapper;

import com.yuxia.blog.entity.User;

public interface UserMapper {

	public int insertUser(User user);
	public User selectUserById(Integer id);
	public User selectUserByUserName(String userName);
	public int updateUserByUserName(User user);
}
