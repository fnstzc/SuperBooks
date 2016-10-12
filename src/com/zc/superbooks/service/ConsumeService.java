package com.zc.superbooks.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.superbooks.bean.ConsumeCostBean;
import com.zc.superbooks.bean.ConsumeExecute;
import com.zc.superbooks.bean.PurposeBean;
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
		List<Consume> consumeList = consumeManager.getAllConsumeList();
		return consumeList;
	}
	
	public PurposeBean getConsumePurposeData(String startTime, String finishTime) {
		return null;
	}
	
	public PurposeBean getConsumePurposeData(String name, String startTime, String finishTime) {
		List<Consume> consumeList = new ArrayList<Consume>();
		
		if (finishTime.compareTo(startTime) < 0 ) {
			return null;
		} else if (finishTime.compareTo(startTime) == 0) {
			consumeList = consumeManager.getConsumeListByNameAndDate(name, startTime);
		} else if (startTime.isEmpty() || startTime == null && finishTime.isEmpty() || finishTime == null) {
			consumeList = consumeManager.getConsumeListByName(name);
		} else if (!startTime.isEmpty() || startTime != null && finishTime.isEmpty() || finishTime == null) {
			consumeList = consumeManager.getConsumeListByNameAndStartTime(name, startTime);
		} else if (startTime.isEmpty() || startTime == null && !finishTime.isEmpty() || finishTime != null) {
			consumeList = consumeManager.getConsumeListByNameAndFinishTime(name, finishTime);
		}
		List<ConsumeCostBean> consumeCostList = consumeExecute.readEachConsumePurpose(consumeList);
		PurposeBean purposeBean = consumeExecute.calculateConsumePurpose(consumeCostList);
		return purposeBean;
	}
}
