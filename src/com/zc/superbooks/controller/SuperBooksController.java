package com.zc.superbooks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zc.superbooks.entity.Consume;
import com.zc.superbooks.entity.Distribution;
import com.zc.superbooks.entity.Income;
import com.zc.superbooks.entity.User;
import com.zc.superbooks.message.MessageCode;
import com.zc.superbooks.service.ConsumeService;
import com.zc.superbooks.service.DistributionService;
import com.zc.superbooks.service.IncomeService;
import com.zc.superbooks.service.TotalFortuneService;
import com.zc.superbooks.service.UserService;

@Controller
public class SuperBooksController {
	@Autowired
	UserService userService;
	@Autowired
	ConsumeService consumeService;
	@Autowired
	DistributionService distributionService;
	@Autowired
	IncomeService incomeService;
	@Autowired
	TotalFortuneService totalFortuneService;
	
	@RequestMapping("/")
	public String init() {
		return "login";
	}
	
	@RequestMapping("/loginCL")
	public String loginCL(User user,Model model) {
		String result = userService.checkUserName(user);
		if (result.equals("success")) {
			model.addAttribute("status", "success");
			return "index";
		} else {
			model.addAttribute("status", "failed");
			return "login";
		}
	}
	
	@RequestMapping("/addConsume")
	public String addConsume(Consume consume, Model model) {
		String result = consumeService.addConsume(consume);
		if (MessageCode.ADD_OK.equals(result)) {
			return "pay_save";
		}
		return "pay_error";
	}
	
	@RequestMapping("/getDistributionList")
	public String getALLDistribution(Model model) {
		List<Distribution> distributionList = distributionService.getDistributionList();
		model.addAttribute("distributionList", distributionList);
		return null;
	}
	
	@RequestMapping("/getDistribution")
	public String getDistribution(String name,Model model) {
		Distribution distribution = distributionService.findDistribution(name);
		model.addAttribute("distribution", distribution);
		return null;
	}
	
	@RequestMapping("/getIncomeList")
	public String getIncome(Model model) {
		List<Income> incomeList = incomeService.getIncomeList();
		model.addAttribute("incomeList", incomeList);
		return null;
	}
	
	@RequestMapping("/analysisConsume")
	public String analysisConsume (Model model) {
		
		return null;
	}
	
	@RequestMapping("/analysisPersonalConsume")
	public String analysisPersonalConsume (String name,Model model) {
		consumeService.getConsumePurposeData(name);
		return null;
	}
}
