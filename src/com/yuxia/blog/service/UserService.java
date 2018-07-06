package com.yuxia.blog.service;

import com.yuxia.blog.entity.User;

public interface UserService {

	public int register(User user);
	public User login(String userName,String password);
	public boolean checkUserName(String userName);
}
