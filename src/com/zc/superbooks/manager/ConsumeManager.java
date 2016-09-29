package com.zc.superbooks.manager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.zc.superbooks.dao.ConsumeDao;
import com.zc.superbooks.entity.Consume;
import com.zc.superbooks.util.MybatisUtil;

public class ConsumeManager {
	@Autowired
	ConsumeDao consumeDao;

	public String addConsume(String name, String identity, String time, String place, 
			String costWay, String cost, String description) {
		SqlSession session = MybatisUtil.getSqlSession();
		consumeDao = session.getMapper(ConsumeDao.class);
		consumeDao.addConsume(name, identity, time, place, costWay, cost, description);
		session.commit();
		session.close();
		
		return "addOK";
	}
	
	public Set<Consume> getConsumeInfo() {
		Set<Consume> consumeInfoSet = new HashSet<Consume>();
		SqlSession session = MybatisUtil.getSqlSession();
		consumeDao = session.getMapper(ConsumeDao.class);
		consumeInfoSet = consumeDao.getAllConsume();
		session.close();
		
		return consumeInfoSet;
	}
}
