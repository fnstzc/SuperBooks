package com.zc.superbooks.dao;

import java.util.List;

import com.zc.superbooks.entity.Distribution;

public interface DistributionDao {
	public List<Distribution> getAllDistribution();

	public void updateDistribution(String name, String total, String crash,
			String alipay, String card, String qqWallet, String weixinWallet,
			String financialProducts,String date);
	public int addDistribution(String name, String total, String crash,
			String alipay, String card, String qqWallet, String weixinWallet,
			String financialProducts,String date);
	public Distribution findDistributionByName(String name);
}
