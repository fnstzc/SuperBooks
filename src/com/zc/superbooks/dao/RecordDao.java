package com.zc.superbooks.dao;

import java.util.List;

import com.zc.superbooks.entity.Record;

public interface RecordDao {
	public void getRecordByName();
	public void getAllRecord();
	public void addRecord(Record record);
	public List<Record> getRecordByTime(String time);
}
