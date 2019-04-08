package com.zzz.springboot.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzz.springboot.dao.IRecordMapper;
import com.zzz.springboot.entity.Record;
import com.zzz.springboot.service.IRecordService;
import com.zzz.springboot.dao.IRecordMapper;

/**
 * <p>Title: RecordService</p>
 * <p>Description: </p>
 * <p>Company: com.zzz</p>
 * @author 张志铮
 * @date 2019年3月30日 下午3:29:46
 */
@Service
public class RecordServiceImpl implements IRecordService {
	@Autowired
	private IRecordMapper iRecordMapper;

	@Override
	public List<Record> selectTeachRecord(String teacher, int state) throws Exception {
		// TODO 自动生成的方法存根
		return this.iRecordMapper.selectRecordByTeacherAndState(teacher, state);
	}

	@Override
	public List<Record> selectStudyRecord(String student, int state) throws Exception {
		// TODO 自动生成的方法存根
		return this.iRecordMapper.selectRecordByStudentAndState(student, state);
	}

	@Override
	public void add(Record record) throws Exception {
		// TODO 自动生成的方法存根
		this.iRecordMapper.insertRecord(record);
	}

	@Override
	public void modify(Record record) throws Exception {
		// TODO 自动生成的方法存根
		this.iRecordMapper.updateRecord(record);
	}


	@Override
	public Record selectSingleRecord(String teacher, String student, Date applyTime) throws Exception {
		// TODO Auto-generated method stub
		return this.iRecordMapper.selectSingleRecord(teacher, student, applyTime);
	}

}
