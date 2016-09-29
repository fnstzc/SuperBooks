package com.zc.superbooks.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.superbooks.dao.DistributionDao;
import com.zc.superbooks.entity.Distribution;
import com.zc.superbooks.manager.DistributionManager;
import com.zc.superbooks.message.MessageCode;
import com.zc.superbooks.util.MybatisUtil;

@Service
public class DistributionService {
	@Autowired
	DistributionManager distributionManager;
	
	public List<Distribution> getDistributionList() {
		List<Distribution> distributionList = distributionManager.getDistributionList();
		return distributionList;
	}
	
	public String addDistribution(String name, String total, String crash,String alipay,
			String card, String qqWallet, String weixinWallet,String financialProducts,String date) {
		String result = distributionManager.addDistribution(name, total, crash, alipay, card, qqWallet, weixinWallet, financialProducts, date);
		return result;
	}
	
	public Distribution findDistribution(String name) {
		Distribution distribution = distributionManager.findDistribution(name);
		return distribution;
	}
}
