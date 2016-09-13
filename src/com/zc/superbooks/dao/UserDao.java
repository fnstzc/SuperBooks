package com.zc.superbooks.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;


import com.zc.superbooks.entity.User;

public interface UserDao {
	public String getUserByName();
	public List<User> getAllUser();
	public User getPasswordByName(String name);
}
