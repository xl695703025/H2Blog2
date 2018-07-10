package com.yuxia.blog.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuxia.blog.entity.User;
import com.yuxia.blog.mapper.UserMapper;
import com.yuxia.blog.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public int register(User user) {
		return userMapper.insertUser(user);
	}

	@Override
	public User login(String userName, String password) {
		User user = userMapper.selectUserByUserName(userName);
		if (null != user && !user.getUserPassword().equals(password)) {
			// 如果用户存在，但是密码不正确
			user = null;
		}
		if (user != null) {
			user.setUserLastLoginTime(new Date());
			userMapper.updateUserByUserName(user);
		}
		return user;
	}

	@Override
	public boolean checkUserName(String userName) {
		User user = userMapper.selectUserByUserName(userName);
		if (user == null)
			return true;
		else
			return false;
	}

	@Override
	public User getUserInfo(String userName) {
		User user = userMapper.selectUserByUserName(userName);
		if(user==null){
			return user;
		}
		System.out.println(user);
		user.setUserEmail(null);
		user.setUserId(-1);
		user.setUserLastLoginTime(null);
		user.setUserRegisterTime(null);
		user.setUserPassword(null);
		return user;
	}
}
