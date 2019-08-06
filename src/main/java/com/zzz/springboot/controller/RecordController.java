package com.zzz.springboot.controller;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zzz.springboot.entity.Record;
import com.zzz.springboot.entity.User;
import com.zzz.springboot.service.IRecordService;
import com.zzz.springboot.service.IUserService;

@Controller
@RequestMapping("/record")
public class RecordController {
	@Autowired
	private IRecordService iRecordService;
	@Autowired
	private IUserService iUserService;

	@RequestMapping(value = "applyRecord", method = RequestMethod.POST)
	public String applyRecord(Record record, String money, ModelMap modelMap, HttpServletRequest request)
			throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		record.setStudent(user.getUsername());
		System.out.println(user);
		System.out.println(record);
		if (user.getMoney() - record.getMoney() >= 0) {

			List<Record> recordList = iRecordService.selectRecordByStudent(user.getUsername());
			System.out.println(record);
			for (Record record1 : recordList) {
				if ((record1.getTeacher().equals(record.getTeacher()))
						&& (record1.getLanguage().equals(record.getLanguage()))) {
					if (!((record.getStartTime().getTime() > record1.getEndTime().getTime())
							|| (record.getEndTime().getTime() < record1.getStartTime().getTime()))) {
						modelMap.put("result", "时间冲突");
						return "userComponent/applySuccess";
					}
				}
			}
			record.setState(0);
			record.setApplyTime(new Timestamp(System.currentTimeMillis()));
			record.setMoney(Integer.valueOf(money));
			iRecordService.add(record);
			user.setMoney(user.getMoney() - record.getMoney());
			iUserService.modify(user);
			modelMap.put("record", record);
			modelMap.put("price", Integer.valueOf(money));
			modelMap.put("result", "申请成功");
		} else {
			modelMap.put("result", "余额不足");

		}
		return "userComponent/applySuccess";
	}

	@RequestMapping(value = "selectSingleRecord/{teacher}/{student}/{applyTime}")
	public String selectSingleRecord(@PathVariable("teacher") String teacher, @PathVariable("student") String student,
			@PathVariable("applyTime") Timestamp applyTime, ModelMap modelMap) throws Exception {
		Record record = iRecordService.selectSingleRecord(teacher, student, applyTime);
		modelMap.put("record", record);
		return "recordInfo";
	}

	@RequestMapping(value = "cancelRecord", method = RequestMethod.POST)
	public String cancelRecord(String teacher, String student, Date applyTime) throws Exception {
		Record record = iRecordService.selectSingleRecord(teacher, student, applyTime);
		record.setState(5);// 目前5为取消订单状态
		return "recordShow";
	}

	@RequestMapping(value = "modifyRecord")
	public String modifyRecord(String teacher, String student, Timestamp applyTime, String remark, Integer star,
			ModelMap modelMap) throws Exception {
		Record record = iRecordService.selectSingleRecord(teacher, student, applyTime);
		System.out.println(star);
		record.setRemark(remark);
		record.setStar(star);
		iRecordService.modify(record);
		modelMap.put("record", record);
		return "recordInfo";
	}

	@RequestMapping(value = "solveRecord")
	public String solveRecord(String teacher, String student, Timestamp applyTime, Integer state,Double discount, ModelMap modelMap)
			throws Exception {
		System.out.println(teacher + student + applyTime + state);
		Record record = iRecordService.selectSingleRecord(teacher, student, applyTime);
		record.setState(state);
		iRecordService.modify(record);
		modelMap.put("record", record);
		if (state == 5) {
			if(discount == 0.9)
			{
				User user2 = iUserService.selectUserByUsername(teacher);
				user2.setMoney(user2.getMoney()+(int)(record.getMoney()*0.05));
			}
			User user = iUserService.selectUserByUsername(student);
			user.setMoney(user.getMoney() + (int)(record.getMoney()*discount));
			iUserService.modify(user);
		}
		return "recordInfo";
	}

	@RequestMapping(value = "showTeach/{teacher}")
	public String selectTeach(@PathVariable("teacher") String teacher, ModelMap modelMap) throws Exception {
		int n = 4;
		List<Record>[] records = new ArrayList[n];
		for (int i = 0; i < n; i++)
			records[i] = iRecordService.selectTeachRecord(teacher, i);
		modelMap.put("records", records);
		return "teachRecord";
	}
	
	@RequestMapping(value = "showRemark")
	public String showRemark(String teacher, ModelMap modelMap) throws Exception {
		List<Record> remarkRecords = new ArrayList<>();
		remarkRecords = iRecordService.selectTeachRecord(teacher, 3);
		modelMap.put("remarkRecords", remarkRecords);
		System.out.println(remarkRecords);
		return "userComponent/remark";
	}

	@RequestMapping(value = "showStudy/{student}")
	public String selectStudy(@PathVariable("student") String student, ModelMap modelMap) throws Exception {
		int n = 4;
		List<Record>[] records = new ArrayList[n];
		for (int i = 0; i < n; i++)
			records[i] = iRecordService.selectStudyRecord(student, i);
		modelMap.put("records", records);
		return "studyRecord";
	}

	
	
	@RequestMapping(value = "refundApply", method = RequestMethod.POST)
	@ResponseBody
	public String refundApply(String teacher, String student, String applyTime,String reason,MultipartFile file,HttpServletRequest request) throws Exception {
		System.out.println("asdasd");
		String extName = ".zip";
		if (file != null) {
			System.out.println("aa"+teacher);
			String oriName = file.getOriginalFilename();
			if (oriName != null && oriName.length() > 0) {
				// 把图片保存到图片目录下
				// 保存图片，这个图片有的时候文件名可能会重复，你保存多了会把原来的图片给覆盖掉，这就不太合适了。
				// 所以为每个文件生成一个新的文件名
				// 截取文件的扩展名(如.jpg)
				extName = oriName.substring(oriName.lastIndexOf("."));
				// 保存文件
				
				File path = new File(ResourceUtils.getURL("classpath:").getPath());
				File f=new File("D:\\upload\\"+teacher+"-"+student+"-"+applyTime.replace(".", "").replace(":", "")+ extName);
				if(f!=null)
					f.delete();
				file.transferTo(new File("D:\\upload\\"+teacher+"-"+student+"-" +applyTime.replace(".", "").replace(":", "")+ extName));
			}
		}
		return "客服人员正在处理，三天内给您答复";
	}
}
