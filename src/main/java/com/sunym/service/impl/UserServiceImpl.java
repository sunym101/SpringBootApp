package com.sunym.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunym.mapper.UserMapper;
import com.sunym.model.UserModel;
import com.sunym.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public UserModel getUserByName(String username) {
		return userMapper.getUserByName(username);
	}
}
