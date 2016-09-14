package com.zc.superbooks.manager;


import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.zc.superbooks.dao.UserDao;
import com.zc.superbooks.entity.User;
import com.zc.superbooks.util.MybatisUtil;

public class UserManager {

	public String checkUser (String name) {
		SqlSession session = MybatisUtil.getSqlSession();
		UserDao userDao = session.getMapper(UserDao.class);
		User user = userDao.getPasswordByName(name);
		session.close();
		if (user.getPassword().equals("root")) {
			return "success";
		} else {
			return "failed";
		}
	}
	public String addConsume(String name, String identity, String time, String place, 
			String costWay, String cost, String desc) {
		SqlSession session = MybatisUtil.getSqlSession();
		UserDao userDao = session.getMapper(UserDao.class);
		User user = userDao.getPasswordByName(name);
		session.close();
		if (user.getPassword().equals("root")) {
			return "success";
		} else {
			return "failed";
		}
		return null;
	}
}
