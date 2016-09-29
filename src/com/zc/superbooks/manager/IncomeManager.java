package com.zc.superbooks.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.zc.superbooks.dao.IncomeDao;
import com.zc.superbooks.entity.Income;
import com.zc.superbooks.message.MessageCode;
import com.zc.superbooks.util.MybatisUtil;

@Service
public class IncomeManager {
	public List<Income> getIncomeList() {
		SqlSession session = MybatisUtil.getSqlSession();
		IncomeDao incomeDao = session.getMapper(IncomeDao.class);
		List<Income> IncomeList = incomeDao.getAllIncome();
		session.close();
		if (IncomeList.isEmpty() || IncomeList == null) {
			return new ArrayList<Income>();
		}
		
		return IncomeList;
	}
	
	public String addIncome(String name, String identity, String time, String incomeWay, String fortune) {
		int addResultNum = 0;
		SqlSession session = MybatisUtil.getSqlSession();
		IncomeDao incomeDao = session.getMapper(IncomeDao.class);
		addResultNum = incomeDao.addIncome(name, identity, time, incomeWay, fortune);
		session.commit();
		session.close();
		
		if (addResultNum != 0) {
			return MessageCode.ADD_OK;
		} else {
			return MessageCode.ADD_FAILED;
		}
	}
	
	public Income findIncome(String name) {
		SqlSession session = MybatisUtil.getSqlSession();
		IncomeDao incomeDao = session.getMapper(IncomeDao.class);
		Income income = incomeDao.findIncomeByName(name);
		if (income == null) {
			return new Income();
		}
		
		return income;
	}
}
