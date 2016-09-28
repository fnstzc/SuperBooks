package com.zc.superbooks.dao;

import java.util.List;
import java.util.Set;

import com.zc.superbooks.entity.Consume;

public interface ConsumeDao {
	public Set<Consume> getAllConsume();
	public void addConsume(String name, String identity, String time,
			String place, String costWay, String cost, String description);
}
