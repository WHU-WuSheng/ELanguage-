package com.zzz.springboot.entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Record {
	private String teacher;
	private String student;
	private String language;
	private Timestamp applyTime;
	private Timestamp startTime;
	private Timestamp endTime;
	private String remark;
	private Integer money;
	private Integer star=0;
	private Integer state;
}
