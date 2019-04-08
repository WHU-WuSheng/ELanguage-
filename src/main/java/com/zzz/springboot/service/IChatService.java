package com.zzz.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zzz.springboot.entity.Chat;

/**
 * <p>Title: IChatService</p>
 * <p>Description: </p>
 * <p>Company: com.zzz</p>
 * @author 张志铮
 * @date 2019年3月27日 下午11:59:49
 */
public interface IChatService {
	List<Chat> select(String sender, String receiver) throws Exception;

	List<String> selectContact(String username) throws Exception;

	void add(Chat chat) throws Exception;
}
