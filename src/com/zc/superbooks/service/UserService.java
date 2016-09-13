package com.zc.superbooks.service;

import org.springframework.stereotype.Service;

import com.zc.superbooks.manager.UserManager;

@Service
public class UserService {
	UserManager userManager = new UserManager();
	
	public String checkUser(String name) {
		String result = userManager.checkUser(name);
		return result;
	}
}
