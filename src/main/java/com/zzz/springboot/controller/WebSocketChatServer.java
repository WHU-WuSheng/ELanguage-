package com.zzz.springboot.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.zzz.springboot.entity.Chat;
import com.zzz.springboot.entity.User;
import com.zzz.springboot.sensitivefilter.SensitiveFilter;
import com.zzz.springboot.service.IChatService;

/**
 * WebSocket 聊天服务端
 *
 * @see ServerEndpoint WebSocket服务端 需指定端点的访问路径
 * @see Session   WebSocket会话对象 通过它给客户端发送消息
 */

@Component
@ServerEndpoint("/chat/{username}")
public class WebSocketChatServer {
	//  这里使用静态，让 service 属于类
	private static IChatService iChatService;
	SensitiveFilter sensitiveFilter = new SensitiveFilter(new
			BufferedReader(new InputStreamReader(
					  ClassLoader.getSystemResourceAsStream("sensi_words.txt") ,
					  StandardCharsets.UTF_8)));
	
//	private static SensitiveFilter sensitiveFilter;
	

	// 注入的时候，给类的 service 注入
	@Autowired
	public void setChatService(IChatService iChatService) {
		WebSocketChatServer.iChatService = iChatService;
	}

	
	/*
	 * @Autowired public void setSensitiveFilter(SensitiveFilter sensitiveFilter) {
	 * WebSocketChatServer.sensitiveFilter = new SensitiveFilter(new
	 * BufferedReader(new InputStreamReader(
	 * ClassLoader.getSystemResourceAsStream("sensi_words.txt") ,
	 * StandardCharsets.UTF_8))); }
	 */
	/**
	 * 全部在线会话  PS: 基于场景考虑 这里使用线程安全的Map存储会话对象。
	 */
	private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

	/**
	 * 当客户端打开连接：1.添加会话对象 2.更新在线人数
	 */
	@OnOpen
	public void onOpen(@PathParam("username") String username, Session session) {
		onlineSessions.put(username, session);
		//sendMessageToAll(Chat.jsonStr("", "", new Timestamp(0), "", onlineSessions.size()));
	}

	/**
	 * 当客户端发送消息：1.获取它的用户名和消息 2.发送消息给所有人
	 * <p>
	 * PS: 这里约定传递的消息为JSON字符串 方便传递更多参数！
	 * @throws Exception 
	 */
	@OnMessage
	public void onMessage(Session session, String jsonStr) throws Exception {
		
		
		
		
		
		
		

		Chat chat = JSON.parseObject(jsonStr, Chat.class);
		
		String filted = sensitiveFilter.filter(chat.getContent(), '*');
		
		
		chat.setContent(filted);
		iChatService.add(new Chat(chat.getSender(), chat.getReceiver(), new Timestamp(System.currentTimeMillis()),
				chat.getContent(), 0,1));
		
		sendMessageToOne(chat.getSender(), Chat.jsonStr(chat.getSender(), chat.getReceiver(),
				new Timestamp(System.currentTimeMillis()), chat.getContent(), onlineSessions.size(),1));
		if (onlineSessions.get(chat.getReceiver()) != null)
			sendMessageToOne(chat.getReceiver(), Chat.jsonStr(chat.getSender(), chat.getReceiver(),
					new Timestamp(System.currentTimeMillis()), chat.getContent(), onlineSessions.size(),1));
	}

	/**
	 * 当关闭连接：1.移除会话对象 2.更新在线人数
	 */
	@OnClose
	public void onClose(Session session) {
		onlineSessions.forEach((id, item) -> {
			if (item == session) {
				onlineSessions.remove(id);
			}
		});
		//sendMessageToAll(Message.jsonStr(Message.QUIT, "", "", onlineSessions.size()));
	}

	/**
	 * 当通信发生异常：打印错误日志
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		error.printStackTrace();
	}

	/**
	 * 公共方法：发送信息给所有人
	 */
	private static void sendMessageToAll(String chat) {
		onlineSessions.forEach((id, session) -> {
			try {
				session.getBasicRemote().sendText(chat);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	private static void sendMessageToOne(String username, String chat) {
		onlineSessions.forEach((id, session) -> {
			if (id.equals(username)) {
				try {
//					session.
					session.getBasicRemote().sendText(chat);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
