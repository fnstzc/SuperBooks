package com.zc.superbooks.dao;

import java.util.List;

import com.zc.superbooks.entity.Consume;

public interface ConsumeDao {
	public List<Consume> getAllConsume();
	public int addConsume(Consume consume);
	public List<Consume> getConsumeListByName(String name);
}
