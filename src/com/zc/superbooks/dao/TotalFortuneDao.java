package com.zc.superbooks.dao;

import java.util.List;

import com.zc.superbooks.entity.TotalFortune;


public interface TotalFortuneDao {
	public List<TotalFortune> getAllTotalFortune();

	public int addTotalFortune(String fortune, String totalCost,
			String totalIncome, String pastCost, String pastIncome,
			String rate, String date);
}
