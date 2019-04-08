package com.zzz.springboot.entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>Title: Record</p>
 * <p>Description: </p>
 * <p>Company: com.zzz</p>
 * @author 张志铮
 * @date 2019年4月2日 下午10:50:13
 */
@Data
@AllArgsConstructor
public class Record {
	private String teacher;
	private String student;
	private String language;
	private Timestamp applyTime;
	private Timestamp startTime;
	private Timestamp endTime;
	private String remark;
	private Integer money;
	private Integer star;
	private Integer state;
}
