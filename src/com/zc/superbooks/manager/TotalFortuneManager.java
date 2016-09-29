package com.zc.superbooks.manager;

import java.util.ArrayList;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.zc.superbooks.dao.TotalFortuneDao;
import com.zc.superbooks.entity.TotalFortune;
import com.zc.superbooks.message.MessageCode;
import com.zc.superbooks.util.MybatisUtil;

@Service
public class TotalFortuneManager {
	public List<TotalFortune> getTotalFortuneList() {
		SqlSession session = MybatisUtil.getSqlSession();
		TotalFortuneDao TotalFortuneDao = session.getMapper(TotalFortuneDao.class);
		List<TotalFortune> TotalFortuneList = TotalFortuneDao.getAllTotalFortune();
		session.close();
		if (TotalFortuneList.isEmpty() || TotalFortuneList == null) {
			return new ArrayList<TotalFortune>();
		}
		
		return TotalFortuneList;
	}
	
	public String addTotalFortune(String fortune, String totalCost,String totalIncome, String pastCost,String pastIncome,String rate, String date) {
		int addResultNum = 0;
		SqlSession session = MybatisUtil.getSqlSession();
		TotalFortuneDao TotalFortuneDao = session.getMapper(TotalFortuneDao.class);
		addResultNum = TotalFortuneDao.addTotalFortune(fortune, totalCost,totalIncome, pastCost, pastIncome,rate,date);
		session.commit();
		session.close();
		
		if (addResultNum != 0) {
			return MessageCode.ADD_OK;
		} else {
			return MessageCode.ADD_FAILED;
		}
	}
}
