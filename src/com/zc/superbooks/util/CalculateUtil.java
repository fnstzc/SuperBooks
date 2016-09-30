package com.zc.superbooks.util;

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
}
