package com.zc.superbooks.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zc.superbooks.entity.Consume;
import com.zc.superbooks.service.ConsumeService;
import com.zc.superbooks.service.UserService;

@Controller
public class SuperBooksController {
	@Autowired
	UserService userService;
	@Autowired
	ConsumeService consumeService;
	
	@RequestMapping("/")
	public String init() {
		return "login";
	}
	
	@RequestMapping("/loginCL")
	public String loginCL(String name,String password,Model model) {
		String result = userService.checkUser(name);
		if (result.equals("success")) {
			model.addAttribute("status", "success");
			return "/getConsumeInfo";
		} else {
			model.addAttribute("status", "failed");
			return "login";
		}
	}
	
	@RequestMapping("/getConsumeInfo")
	public String getConsumeInfo(Model model) {
		Set<Consume> consumeInfoSet = consumeService.getConsumeInfo();
		model.addAttribute("consumeSet", consumeInfoSet);
		return "index";
	}
	
	@RequestMapping("/addConsume")
	public String addConsume(String name, String identity, String time, String place, 
								String costWay, String cost, String desc, Model model) {
		String result = consumeService.addConsume(name, identity, time, place, costWay, cost, desc);
		if (result.equals("addOK")) {
			return "pay_save";
		} else {
			return "pay_add";
		}
	}
}
