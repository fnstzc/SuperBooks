package com.zc.superbooks.model;

import com.zc.superbooks.entity.Consume;
import com.zc.superbooks.entity.Distribution;

public class ConsumeHandle {
	Distribution distribution = new Distribution();
	
	public void setDistributionInfo(Consume consume) {
		distribution.setName(consume.getName());
		distribution.setDate(consume.getDate());
	}
	
	public void setCostToDistribution(Consume consume) {
		String cost = consume.getCost();
		switch (consume.getCostWay()) {
		case "crash":
			distribution.setCrash(cost);
			break;
		case "alipay":
			distribution.setAlipay(cost);
			break;
		case "card":
			distribution.setCard(cost);
			break;
		case "qqWallet":
			distribution.setQqWallet(cost);
			break;
		case "weixinWallet":
			distribution.setWeixinWallet(cost);
			break;
		case "financialProducts":
			distribution.setFinancialProducts(cost);
			break;
		default:
			break;
		}
	}
	
	public String getConsumeWay(Consume consume) {
		String costWay = consume.getCostWay();
		return costWay;
	}
}
