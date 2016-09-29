package com.zc.superbooks.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.zc.superbooks.entity.Consume;
import com.zc.superbooks.manager.ConsumeManager;

public class ConsumeService {
	
	@Autowired
	ConsumeManager consumeManager;
	
	//添加消费信息
	public String addConsume(String name, String identity, String time,
			String place, String costWay, String cost, String description) {
		String result = consumeManager.addConsume(name, identity, time, place, costWay, cost, description);
		return result;
	}
	
	//获取要显示的数据信息
	public Set<Consume> getConsumeInfo() {
		Set<Consume> consumeInfoSet = new HashSet<Consume>();
		consumeInfoSet = consumeManager.getConsumeInfo();
		return consumeInfoSet;
	}
}
