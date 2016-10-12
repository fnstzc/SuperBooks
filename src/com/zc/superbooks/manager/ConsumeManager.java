package com.zc.superbooks.manager;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
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
	
	public List<Consume> getAllConsumeList() {
		SqlSession session = MybatisUtil.getSqlSession();
		ConsumeDao consumeDao = session.getMapper(ConsumeDao.class);
		List<Consume> consumeList = consumeDao.getAllConsume();
		session.close();
		return consumeList;
	}
	
	
	public List<Consume> getConsumeListByName(String name) {
		SqlSession session = MybatisUtil.getSqlSession();
		ConsumeDao consumeDao = session.getMapper(ConsumeDao.class);
		List<Consume> consumeList = consumeDao.getConsumeListByName(name);
		session.close();
		return consumeList;
	}
	
	public List<Consume> getConsumeListByNameAndStartTime(String name, String startTime) {
		SqlSession session = MybatisUtil.getSqlSession();
		ConsumeDao consumeDao = session.getMapper(ConsumeDao.class);
		List<Consume> consumeList = consumeDao.getConsumeListByNameAndStartTime(name,startTime);
		session.close();
		return consumeList;
	}
	public List<Consume> getConsumeListByNameAndFinishTime(String name, String finishTime) {
		SqlSession session = MybatisUtil.getSqlSession();
		ConsumeDao consumeDao = session.getMapper(ConsumeDao.class);
		List<Consume> consumeList = consumeDao.getConsumeListByNameAndFinishTime(name,finishTime);
		session.close();
		return consumeList;
	}
	public List<Consume> getConsumeListByNameAndTimeBlock(String name, String startTime, String finishTime) {
		SqlSession session = MybatisUtil.getSqlSession();
		ConsumeDao consumeDao = session.getMapper(ConsumeDao.class);
		List<Consume> consumeList = consumeDao.getConsumeListByNameAndTimeBlock(name,startTime,finishTime);
		session.close();
		return consumeList;
	}
	public List<Consume> getConsumeListByNameAndDate(String name, String date) {
		SqlSession session = MybatisUtil.getSqlSession();
		ConsumeDao consumeDao = session.getMapper(ConsumeDao.class);
		List<Consume> consumeList = consumeDao.getConsumeListByNameAndDate(name, date);
		session.close();
		return consumeList;
	} 
}
