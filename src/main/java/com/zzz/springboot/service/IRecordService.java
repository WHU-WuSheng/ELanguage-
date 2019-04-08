package com.zzz.springboot.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zzz.springboot.entity.Record;

/**
 * <p>Title: IRecordService</p>
 * <p>Description: </p>
 * <p>Company: com.zzz</p>
 * @author 张志铮
 * @date 2019年3月27日 下午11:59:59
 */
public interface IRecordService {
	List<Record> selectTeachRecord(String teacher, int state) throws Exception;

	List<Record> selectStudyRecord(String student, int state) throws Exception;

	Record selectSingleRecord(String teacher, String student, Date applyTime) throws Exception;
	
	void add(Record record) throws Exception;

	void modify(Record record) throws Exception;

}
