package com.zc.superbooks.dao;

import java.util.List;

import com.zc.superbooks.entity.Distribution;

public interface DistributionDao {
	public List<Distribution> getAllDistribution();

	public void updateDistribution(Distribution distribution);
	public int addDistribution(Distribution distribution);
	public Distribution findDistributionByName(String name);
	public Distribution findUpToDateDistributionByName(String name);
}
