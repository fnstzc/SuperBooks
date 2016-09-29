package com.zc.superbooks.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.superbooks.dao.DistributionDao;
import com.zc.superbooks.entity.Distribution;
import com.zc.superbooks.entity.TotalFortune;
import com.zc.superbooks.manager.DistributionManager;
import com.zc.superbooks.manager.TotalFortuneManager;
import com.zc.superbooks.message.MessageCode;
import com.zc.superbooks.util.MybatisUtil;

@Service
public class TotalFortuneService {
	@Autowired
	TotalFortuneManager totalFortuneManager;
	
	public List<TotalFortune> getTotalFortuneList() {
		List<TotalFortune> TotalFortuneList = totalFortuneManager.getTotalFortuneList();
		return TotalFortuneList;
	}
	
	public String addTotalFortune(String fortune, String totalCost,String totalIncome, String pastCost,String pastIncome,String rate, String date) {
		String result = totalFortuneManager.addTotalFortune(fortune, totalCost,totalIncome, pastCost, pastIncome,rate,date);
		return result;
	}
	
	
}
