package com.zzz.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzz.springboot.dao.IChatMapper;
import com.zzz.springboot.entity.Chat;
import com.zzz.springboot.entity.NewInfo;
import com.zzz.springboot.service.IChatService;

/**
 * <p>Title: ChatService</p>
 * <p>Description: </p>
 * <p>Company: com.zzz</p>
 * @author 张志铮
 * @date 2019年3月30日 下午3:29:43
 */
@Service
public class ChatServiceImpl implements IChatService {
	@Autowired
	private IChatMapper iChatMapper;

	@Override
	public List<Chat> select(String sender, String receiver) throws Exception {
		// TODO 自动生成的方法存根
		return this.iChatMapper.selectChat(sender, receiver);
	}

	@Override
	public void add(Chat chat) throws Exception {
		// TODO 自动生成的方法存根
		this.iChatMapper.insertChat(chat);
	}

	@Override
	public List<String> selectContact(String username) throws Exception {
		// TODO 自动生成的方法存根
		return this.iChatMapper.selectContact(username);
	}

	@Override
	public List<NewInfo> selectNewInfos(String username) throws Exception {
		// TODO Auto-generated method stub
		return this.iChatMapper.selectNewinfos(username);
	}

	@Override
	public void hasRead(String from, String to) throws Exception {
		// TODO Auto-generated method stub
		this.iChatMapper.hasRead(from, to);
	}

}