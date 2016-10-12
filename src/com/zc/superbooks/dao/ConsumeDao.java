package com.zc.superbooks.dao;

import java.util.List;

import com.zc.superbooks.entity.Consume;

public interface ConsumeDao {
	public List<Consume> getAllConsume();
	public int addConsume(Consume consume);
	public List<Consume> getConsumeListByName(String name);
	public List<Consume> getConsumeListByNameAndStartTime(String name, String startTime);
	public List<Consume> getConsumeListByNameAndFinishTime(String name, String finishTime);
	public List<Consume> getConsumeListByNameAndTimeBlock(String name, String startTime, String finishTime);
	public List<Consume> getConsumeListByNameAndDate(String name, String date);
}
