package com.zc.superbooks.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zc.superbooks.service.UserService;
import com.zc.superbooks.util.MybatisUtil;

@Controller
public class SuperBooksController {
	@Autowired
	UserService userService;
	
	@RequestMapping("/")
	public String init() {
		return "login";
	}
	
	@RequestMapping("/loginCL")
	public String loginCL(@RequestParam("name") String name, @RequestParam("password") String password,Model model) {
		String result = userService.checkUser(name);
		if (result.equals("success")) {
			model.addAttribute("status", "success");
			return "index";
		} else {
			model.addAttribute("status", "failed");
			return "login";
		}
	}
}
