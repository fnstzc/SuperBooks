package com.zc.superbooks.manager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.superbooks.dao.ConsumeDao;
import com.zc.superbooks.entity.Consume;
import com.zc.superbooks.message.MessageCode;
import com.zc.superbooks.util.MybatisUtil;

@Service
public class ConsumeManager {

	public String addConsume(Consume consume) {
		SqlSession session = MybatisUtil.getSqlSession();
		ConsumeDao consumeDao = session.getMapper(ConsumeDao.class);
		consumeDao.addConsume(consume);
		session.commit();
		session.close();
		
		return MessageCode.ADD_OK;
	}
	
	public List<Consume> getConsumeList() {
		SqlSession session = MybatisUtil.getSqlSession();
		ConsumeDao consumeDao = session.getMapper(ConsumeDao.class);
		List<Consume> consumeList = consumeDao.getAllConsume();
		session.close();
		
		return consumeList;
	}
}
