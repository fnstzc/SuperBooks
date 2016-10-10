package com.zc.superbooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.superbooks.bean.ConsumeExecute;
import com.zc.superbooks.entity.Consume;
import com.zc.superbooks.manager.ConsumeManager;
import com.zc.superbooks.message.MessageCode;

@Service
public class ConsumeService {
	
	@Autowired
	ConsumeManager consumeManager;
	@Autowired
	ConsumeExecute consumeExecute;
	
	//添加消费信息
	public String addConsume(Consume consume) {
		String result = consumeManager.addConsume(consume);
		if (MessageCode.ADD_OK.equals(result)) {
			consumeExecute.updateDistribution(consume);
			consumeExecute.updateTotalFortune(consume);
		}
		return result;
	}
	
	//获取要显示的数据信息
	public List<Consume> getConsumeList() {
		List<Consume> consumeList = consumeManager.getConsumeList();
		return consumeList;
	}
	
	
}
