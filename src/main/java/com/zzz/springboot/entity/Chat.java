package com.zzz.springboot.entity;

import java.sql.Timestamp;

import com.alibaba.fastjson.JSON;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * <p>Title: Chat</p>
 * <p>Description: </p>
 * <p>Company: com.zzz</p>
 * @author 张志铮
 * @date 2019年4月2日 下午10:50:23
 */
@Data
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

	public static String jsonStr(String sender, String receiver, Timestamp time, String content, Integer onlineCount) {
		return JSON.toJSONString(new Chat(sender, receiver, time, content, onlineCount));
	}
}
