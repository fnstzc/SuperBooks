package com.zc.superbooks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.superbooks.entity.Consume;
import com.zc.superbooks.entity.User;
import com.zc.superbooks.manager.UserManager;

@Service
public class UserService {
	@Autowired
	UserManager userManager;
	
	public String checkUserName(User user) {
		String name = user.getName();
		String result = userManager.checkUserName(name);
		return result;
	}
}
