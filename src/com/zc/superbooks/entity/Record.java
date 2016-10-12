package com.zc.superbooks.entity;

import java.sql.Date;

public class Record {
	private Date date;
	private String name;
	private String action;
	private String operate;
	private String money;
	private String operateWay;
	private String restMoney;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getOperateWay() {
		return operateWay;
	}
	public void setOperateWay(String operateWay) {
		this.operateWay = operateWay;
	}
	public String getRestMoney() {
		return restMoney;
	}
	public void setRestMoney(String restMoney) {
		this.restMoney = restMoney;
	}
}
