package com.zzz.springboot.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zzz.springboot.entity.Record;
import com.zzz.springboot.service.IRecordService;

@Controller
@RequestMapping("/record")
public class RecordController {
	@Autowired
	private IRecordService iRecordService;

	@RequestMapping(value = "applyRecord", method = RequestMethod.POST)
	public String applyRecord(Record record, ModelMap modelMap) throws Exception {
		record.setState(0);
		iRecordService.add(record);
		modelMap.put("record", record);
		return "applySuccess";
	}

	@RequestMapping(value = "selectSingleRecord", method = RequestMethod.POST)
	public String selectSingleRecord(String teacher, String student, Date applyTime, ModelMap modelMap)
			throws Exception {
		Record record = iRecordService.selectSingleRecord(teacher, student, applyTime);
		modelMap.put("record", record);
		return "recordInfo";
	}

	@RequestMapping(value = "cancelRecord", method = RequestMethod.POST)
	public String cancelRecord(String teacher, String student, Date applyTime) throws Exception {
		Record record = iRecordService.selectSingleRecord(teacher, student, applyTime);
		record.setState(5);//目前5为取消订单状态
		return "recordShow";
	}

	@RequestMapping(value = "showTeach/{teacher}", method = RequestMethod.POST)
	public String selectTeach(@PathVariable("teacher") String teacher, ModelMap modelMap) throws Exception {
		int n = 4;
		List<Record>[] records = new ArrayList[n];
		for (int i = 0; i < n; i++)
			records[i] = iRecordService.selectTeachRecord(teacher, i);
		modelMap.put("records", records);
		return "teachRecord";
	}

	@RequestMapping(value = "showStudy/{student}", method = RequestMethod.POST)
	public String selectStudy(@PathVariable("student") String student, ModelMap modelMap) throws Exception {
		int n = 4;
		List<Record>[] records = new ArrayList[n];
		for (int i = 0; i < n; i++)
			records[i] = iRecordService.selectStudyRecord(student, i);
		modelMap.put("records", records);
		return "studyRecord";
	}

}
