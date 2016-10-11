package com.zc.superbooks.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.accessibility.internal.resources.accessibility;
import com.zc.superbooks.entity.Consume;
import com.zc.superbooks.entity.Distribution;
import com.zc.superbooks.entity.TotalFortune;
import com.zc.superbooks.manager.ConsumeManager;
import com.zc.superbooks.manager.DistributionManager;
import com.zc.superbooks.manager.TotalFortuneManager;
import com.zc.superbooks.message.PurposeType;
import com.zc.superbooks.util.CalculateUtil;

@Service
public class ConsumeExecute {
	@Autowired
	ConsumeManager consumeManager;
	@Autowired
	DistributionManager distributionManager;
	@Autowired
	TotalFortuneManager totalFortuneManager;
	
	private PurposeBean purposeBean = new PurposeBean();

	public void updateDistribution(Consume consume) {
		Distribution distribution = distributionManager
				.findUpToDateDistributionByName(consume.getName());
		getNewDistribution(consume, distribution);
		addNewDistributionToDB(distribution);
	}

	public void updateTotalFortune(Consume consume) {
		TotalFortune totalFortune = totalFortuneManager
				.findUpToDateTotalFortune();
		getNewTotalFortune(consume, totalFortune);
		addNewTotalFortuneToDB(totalFortune);
	}

	public void addNewTotalFortuneToDB(TotalFortune totalFortune) {
		totalFortuneManager.addTotalFortune(totalFortune);
	}

	public void getNewTotalFortune(Consume consume, TotalFortune totalFortune) {
		updatePastCost(totalFortune);
		updateTotalCost(consume, totalFortune);
		updateRate(totalFortune);
		updateFortune(totalFortune);
		totalFortune.setDate(consume.getDate());
	}

	public void updateFortune(TotalFortune totalFortune) {
		String fortune = Integer.parseInt(totalFortune.getTotalIncome())
				- Integer.parseInt(totalFortune.getTotalCost()) + "";
		totalFortune.setFortune(fortune);
	}

	public void updateRate(TotalFortune totalFortune) {
		String rate = Integer.parseInt(totalFortune.getTotalIncome())
				/ Integer.parseInt(totalFortune.getTotalCost()) + "";
		totalFortune.setRate(rate + "%");
	}

	public void updateTotalCost(Consume consume, TotalFortune totalFortune) {
		String totalCost = Integer.parseInt(consume.getCost())
				+ Integer.parseInt(totalFortune.getTotalCost()) + "";
		totalFortune.setTotalCost(totalCost);
	}

	public void updatePastCost(TotalFortune totalFortune) {
		totalFortune.setPastCost(totalFortune.getTotalCost());
	}

	public void addNewDistributionToDB(Distribution distribution) {
		distributionManager.addDistribution(distribution);
	}

	public void getNewDistribution(Consume consume, Distribution distribution) {
		setConsumeCostToNewDistribution(consume, distribution);
		setConsumeInfoToNewDistribution(consume, distribution);
		setDistributionTotal(distribution);
	}

	public void setDistributionTotal(Distribution distribution) {
		String total = CalculateUtil.calculateDistributionTotal(distribution)
				.toString();
		distribution.setTotal(total);
	}

	public void wrapDistribution(Distribution distribution,
			Distribution pastDistribution) {
		distribution.setAlipay(pastDistribution.getAlipay());
		distribution.setCard(pastDistribution.getCard());
		distribution.setCrash(pastDistribution.getCrash());
		distribution.setFinancialProducts(pastDistribution
				.getFinancialProducts());
		distribution.setQqWallet(pastDistribution.getFinancialProducts());
		distribution.setWeixinWallet(pastDistribution.getWeixinWallet());
	}

	// public void addConsumeCostToDistribution(Consume consume,Distribution
	// pastDistribution) {
	// if (distribution.getCrash() != null) {
	// int pastCrash = Integer.parseInt(pastDistribution.getCrash());
	// String newCrash = Integer.parseInt(distribution.getCrash()) + pastCrash +
	// "";
	// distribution.setCrash(newCrash);
	// }
	// if (distribution.getAlipay() != null) {
	// int pastAlipay = Integer.parseInt(pastDistribution.getAlipay());
	// String newAlipay = Integer.parseInt(distribution.getAlipay()) +
	// pastAlipay + "";
	// distribution.setAlipay(newAlipay);
	// }
	// if (distribution.getCard() != null) {
	// int pastCard = Integer.parseInt(pastDistribution.getCard());
	// String newCard = Integer.parseInt(distribution.getCard()) + pastCard +
	// "";
	// distribution.setCard(newCard);
	// }
	// if (distribution.getQqWallet() != null) {
	// int pastQqWallet = Integer.parseInt(pastDistribution.getQqWallet());
	// String newQqWallet = Integer.parseInt(distribution.getQqWallet()) +
	// pastQqWallet + "";
	// distribution.setQqWallet(newQqWallet);
	// }
	// if (distribution.getWeixinWallet() != null) {
	// int pastWeixinWallet =
	// Integer.parseInt(pastDistribution.getWeixinWallet());
	// String newWeixinWallet = Integer.parseInt(distribution.getWeixinWallet())
	// + pastWeixinWallet + "";
	// distribution.setWeixinWallet(newWeixinWallet);
	// }
	// if (distribution.getFinancialProducts() != null) {
	// int pastFinancialProduct =
	// Integer.parseInt(pastDistribution.getFinancialProducts());
	// String newFinancialProduct =
	// Integer.parseInt(distribution.getFinancialProducts()) +
	// pastFinancialProduct + "";
	// distribution.setFinancialProducts(newFinancialProduct);
	// }
	// }

	public void setConsumeInfoToNewDistribution(Consume consume,
			Distribution distribution) {
		distribution.setName(consume.getName());
		distribution.setDate(consume.getDate());
	}

	public void setConsumeCostToNewDistribution(Consume consume,
			Distribution distribution) {
		switch (consume.getCostWay()) {
		case "crash":
			String newCrash = Integer.parseInt(consume.getCost())
					+ Integer.parseInt(distribution.getCrash()) + "";
			distribution.setCrash(newCrash);
			break;
		case "alipay":
			String newAlipay = Integer.parseInt(consume.getCost())
					+ Integer.parseInt(distribution.getAlipay()) + "";
			distribution.setAlipay(newAlipay);
			break;
		case "card":
			String newCard = Integer.parseInt(consume.getCost())
					+ Integer.parseInt(distribution.getCard()) + "";
			distribution.setCard(newCard);
			break;
		case "qqWallet":
			String newQqWallet = Integer.parseInt(consume.getCost())
					+ Integer.parseInt(distribution.getQqWallet()) + "";
			distribution.setQqWallet(newQqWallet);
			break;
		case "weixinWallet":
			String newWeixinWallet = Integer.parseInt(consume.getCost())
					+ Integer.parseInt(distribution.getWeixinWallet()) + "";
			distribution.setWeixinWallet(newWeixinWallet);
			break;
		case "financialProducts":
			String newFinancialProduct = Integer.parseInt(consume.getCost())
					+ Integer.parseInt(distribution.getFinancialProducts())
					+ "";
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

	public void setConsumeDateToTotalFortune(String date,
			TotalFortune totalFortune) {
		totalFortune.setDate(date);
	}
	
	public List<ConsumeCostBean> readEachConsumePurpose(List<Consume> consumeList) {
		ConsumeCostBean ccb = new ConsumeCostBean();
		List<ConsumeCostBean> consumeCostList = new ArrayList<ConsumeCostBean>();
		for (Consume consume : consumeList) {
			ccb.setCost(Integer.parseInt(consume.getCost()));
			ccb.setPurpose(consume.getPurpose());
			consumeCostList.add(ccb);
		}
		return consumeCostList;
	}

	public void calculateConsumePurpose(String name) {
		List<Consume> consumeList = consumeManager.getConsumeListByName(name);
		for (Consume consume : consumeList) {
			switch (consume.getPurpose()) {
			case "shopping":
				purposeBean.setShopping(Integer.parseInt(consume.getCost()));
				break;
			case "book":

				break;
			case "communication":

				break;
			case "food":

				break;
			case "dog":

				break;
			case "traffic":

				break;
			case "ticket":

				break;
			case "treat":

				break;
			case "party":

				break;

			default:
				break;
			}
		}
	}
}
