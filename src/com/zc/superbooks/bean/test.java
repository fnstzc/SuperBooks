package com.zc.superbooks.bean;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.crypto.Data;

import org.apache.ibatis.session.SqlSession;

import com.zc.superbooks.dao.ConsumeDao;
import com.zc.superbooks.dao.RecordDao;
import com.zc.superbooks.entity.Consume;
import com.zc.superbooks.entity.Record;
import com.zc.superbooks.util.MybatisUtil;

public class test {
	
	public static void main(String[] args) {
		Record record = new Record();
		record.setDate("2016-5-18");
		SqlSession session = MybatisUtil.getSqlSession();
		RecordDao RecordDao = session.getMapper(RecordDao.class);
		RecordDao.addRecord(record);
//		if (recordList == null || recordList.isEmpty()) {
//			System.out.println("no data");
//		} else {
//			for (Record record : recordList) {
//				System.out.println(record.getDate());
//			}
//		}
		session.commit();
		session.close();
	}
}
