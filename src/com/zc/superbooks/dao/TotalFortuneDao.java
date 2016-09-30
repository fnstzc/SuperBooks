package com.zc.superbooks.dao;

import java.util.List;

import com.zc.superbooks.entity.TotalFortune;


public interface TotalFortuneDao {
	public List<TotalFortune> getAllTotalFortune();
	public int addTotalFortune(TotalFortune totalFortune);
	public TotalFortune getUpToDateFortune();
}
