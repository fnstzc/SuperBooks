package com.zc.superbooks.util;

import java.util.Set;

import com.zc.superbooks.entity.Distribution;

public class CalculateUtil {
	public static Integer calculateDistributionTotal(Distribution distribution) {
		int alipay = Integer.parseInt(distribution.getAlipay());
		int card = Integer.parseInt(distribution.getCard());
		int crash = Integer.parseInt(distribution.getCrash());
		int financialProducts = Integer.parseInt(distribution.getFinancialProducts());
		int qqWallet = Integer.parseInt(distribution.getQqWallet());
		int weixinWallet = Integer.parseInt(distribution.getWeixinWallet());
		
		int total = alipay + card + crash + financialProducts + qqWallet + weixinWallet;
		
		return total;
	}
	
	
	public String getTotal(Set<String> targetSet) {
		if (targetSet.isEmpty()) {
			return null;
		} else {
			int result = 0;
			for (String target : targetSet) {
				result = Integer.parseInt(target) + result;
			}
			return result+"";			
		}
	}
}
