package com.zc.superbooks.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.zc.superbooks.entity.Consume;

public class test {
	
	public static void main(String[] args) {
		Set<Consume> consumeSet = new HashSet<>();
		Consume consume1 = new Consume();
		consume1.setCost("12");
		consume1.setPurpose("book");
		Consume consume2 = new Consume();
		consume2.setCost("14");
		consume2.setPurpose("shopping");
		
		consumeSet.add(consume1);
		consumeSet.add(consume2);
		
		ConsumeCostBean ccb = new ConsumeCostBean();
		List<ConsumeCostBean> consumeCostList = new ArrayList<ConsumeCostBean>();
		for (Consume consume : consumeSet) {
			ccb.setCost(Integer.parseInt(consume.getCost()));
			consumeCostList.add(ccb);
		}
		for (ConsumeCostBean consumeCostBean : consumeCostList) {
			System.out.println(consumeCostBean.getCost());
		}
	}
}
