package com.zzz.springboot.entity;

import java.sql.Timestamp;

import com.alibaba.fastjson.JSON;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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