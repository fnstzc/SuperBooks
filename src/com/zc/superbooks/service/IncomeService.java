package com.zc.superbooks.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.superbooks.dao.DistributionDao;
import com.zc.superbooks.entity.Distribution;
import com.zc.superbooks.entity.Income;
import com.zc.superbooks.manager.DistributionManager;
import com.zc.superbooks.manager.IncomeManager;
import com.zc.superbooks.message.MessageCode;
import com.zc.superbooks.util.MybatisUtil;

@Service
public class IncomeService {
	@Autowired
	IncomeManager incomeManager;
	
	public List<Income> getIncomeList() {
		List<Income> IncomeList = incomeManager.getIncomeList();
		return IncomeList;
	}
	
	public String addIncome(Income income) {
		String result = incomeManager.addIncome(income);
		return result;
	}
	
	public Income findIncome(String name) {
		Income Income = incomeManager.findIncome(name);
		return Income;
	}
}
