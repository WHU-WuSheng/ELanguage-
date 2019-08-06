package com.zzz.springboot.entity;

import java.sql.Timestamp;

import com.alibaba.fastjson.JSON;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Chat {
	private Integer id;
	@NonNull
	private String sender;
	@NonNull
	private String receiver;
	@NonNull
	private Timestamp time;
	@NonNull
	private String content;
	@NonNull
	private Integer onlineCount;
	@NonNull
	private Integer state;

	public static String jsonStr(String sender, String receiver, Timestamp time, String content, Integer onlineCount,Integer state) {
		return JSON.toJSONString(new Chat(sender, receiver, time, content, onlineCount,state));
	}
}