package com.zc.superbooks.dao;

import java.util.List;

import com.zc.superbooks.entity.Income;

public interface IncomeDao {
	public List<Income> getAllIncome();
	public Income findIncomeByName(String name);
	public int addIncome(String name, String identity, String time, String incomeWay, String fortune);
}
