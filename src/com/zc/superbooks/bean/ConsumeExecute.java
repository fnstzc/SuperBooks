package com.zc.superbooks.bean;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.superbooks.entity.Consume;
import com.zc.superbooks.entity.Distribution;
import com.zc.superbooks.entity.TotalFortune;
import com.zc.superbooks.manager.DistributionManager;
import com.zc.superbooks.manager.TotalFortuneManager;
import com.zc.superbooks.util.CalculateUtil;

@Service
public class ConsumeExecute {
	@Autowired
	DistributionManager distributionManager;
	@Autowired
	TotalFortuneManager totalFortuneManager;
	
	public void updateDistribution(Consume consume) {
		Distribution distribution = getNewDistribution(consume);
		addNewDistributionToDB(distribution);
	}
	
	public void addNewDistributionToDB(Distribution distribution) {
		distributionManager.addDistribution(distribution);
	}
	
	public Distribution getNewDistribution(Consume consume) {
		Distribution newDistribution = distributionManager.findUpToDateDistributionByName(consume.getName());		
		addConsumeCostToNewDistribution(consume, newDistribution);
		setConsumeDateToNewDistribution(consume, newDistribution);
		setDistributionTotal(newDistribution);
		return newDistribution;
	}
	
	public void setDistributionTotal(Distribution distribution) {
		String total = CalculateUtil.calculateDistributionTotal(distribution).toString();
		distribution.setTotal(total);
	}
	
//	public void wrapDistribution(Distribution distribution,Distribution pastDistribution) {
//		distribution.setAlipay(pastDistribution.getAlipay());
//		distribution.setCard(pastDistribution.getCard());
//		distribution.setCrash(pastDistribution.getCrash());
//		distribution.setFinancialProducts(pastDistribution.getFinancialProducts());
//		distribution.setQqWallet(pastDistribution.getFinancialProducts());
//		distribution.setWeixinWallet(pastDistribution.getWeixinWallet());
//	}
//	
//	public void addTwoDistribution(Distribution distribution,Distribution pastDistribution) {
//		if (distribution.getCrash() != null) {
//			int pastCrash = Integer.parseInt(pastDistribution.getCrash());
//			String newCrash = Integer.parseInt(distribution.getCrash()) + pastCrash + "";
//			distribution.setCrash(newCrash);
//		}
//		if (distribution.getAlipay() != null) {
//			int pastAlipay = Integer.parseInt(pastDistribution.getAlipay());
//			String newAlipay = Integer.parseInt(distribution.getAlipay()) + pastAlipay + "";
//			distribution.setAlipay(newAlipay);
//		}
//		if (distribution.getCard() != null) {
//			int pastCard = Integer.parseInt(pastDistribution.getCard());
//			String newCard = Integer.parseInt(distribution.getCard()) + pastCard + "";
//			distribution.setCard(newCard);
//		}
//		if (distribution.getQqWallet() != null) {
//			int pastQqWallet = Integer.parseInt(pastDistribution.getQqWallet());
//			String newQqWallet = Integer.parseInt(distribution.getQqWallet()) + pastQqWallet + "";
//			distribution.setQqWallet(newQqWallet);
//		}
//		if (distribution.getWeixinWallet() != null) {
//			int pastWeixinWallet = Integer.parseInt(pastDistribution.getWeixinWallet());
//			String newWeixinWallet = Integer.parseInt(distribution.getWeixinWallet()) + pastWeixinWallet + "";
//			distribution.setWeixinWallet(newWeixinWallet);
//		}
//		if (distribution.getFinancialProducts() != null) {
//			int pastFinancialProduct = Integer.parseInt(pastDistribution.getFinancialProducts());
//			String newFinancialProduct = Integer.parseInt(distribution.getFinancialProducts()) + pastFinancialProduct + "";
//			distribution.setFinancialProducts(newFinancialProduct);
//		}
//	}
	
	public void setConsumeDateToNewDistribution(Consume consume,Distribution distribution) {
		distribution.setDate(consume.getDate());
	}
	
	public void addConsumeCostToNewDistribution(Consume consume,Distribution distribution) {
		switch (consume.getCostWay()) {
		case "crash":
			int pastCrash = Integer.parseInt(distribution.getCrash());
			String newCrash = Integer.parseInt(consume.getCost()) + pastCrash + "";
			distribution.setCrash(newCrash);
			break;
		case "alipay":
			int pastAlipay = Integer.parseInt(distribution.getCrash());
			String newAlipay = Integer.parseInt(consume.getCost()) + pastAlipay + "";
			distribution.setCrash(newAlipay);
			break;
		case "card":
			int pastCard = Integer.parseInt(distribution.getCard());
			String newCard = Integer.parseInt(consume.getCost()) + pastCard + "";
			distribution.setCard(newCard);
			break;
		case "qqWallet":
			int pastQqWallet = Integer.parseInt(distribution.getQqWallet());
			String newQqWallet = Integer.parseInt(consume.getCost()) + pastQqWallet + "";
			distribution.setQqWallet(newQqWallet);
			break;
		case "weixinWallet":
			int pastWeixinWallet = Integer.parseInt(distribution.getWeixinWallet());
			String newWeixinWallet = Integer.parseInt(consume.getCost()) + pastWeixinWallet + "";
			distribution.setWeixinWallet(newWeixinWallet);
			break;
		case "financialProducts":
			int pastFinancialProduct = Integer.parseInt(distribution.getFinancialProducts());
			String newFinancialProduct = Integer.parseInt(consume.getCost()) + pastFinancialProduct + "";
			distribution.setFinancialProducts(newFinancialProduct);
			break;
		default:
			break;
		}
	}
	
	public String getConsumeWay(Consume consume) {
		String costWay = consume.getCostWay();
		return costWay;
	}
	
	public void updateTotalFortune(Consume consume,TotalFortune totalFortune) {
		
	}
	
	public void subConsumeCostFromFortune(String cost, TotalFortune totalFortune) {
		int pastFortune = Integer.parseInt(totalFortune.getFortune());
		String newFortune = pastFortune - Integer.parseInt(cost) + "";
		totalFortune.setFortune(newFortune);
	}
	public void addTotalCost(String cost, TotalFortune totalFortune) {
		int pastTotalCost = Integer.parseInt(totalFortune.getTotalCost());
		String newTotalCost = Integer.parseInt(cost) + pastTotalCost + "";
		totalFortune.setTotalCost(newTotalCost);
	}
	public void setConsumeDateToTotalFortune(String date,TotalFortune totalFortune) {
		totalFortune.setDate(date);
	}
}
