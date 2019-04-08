package com.zzz.springboot.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.zzz.springboot.entity.Chat;

/**
 * WebSocket 聊天服务端
 *
 * @see ServerEndpoint WebSocket服务端 需指定端点的访问路径
 * @see Session   WebSocket会话对象 通过它给客户端发送消息
 */

@Component
@ServerEndpoint("/chat/{username}")
public class WebSocketChatServer {

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
	 */
	@OnMessage
	public void onMessage(Session session, String jsonStr) {
		Chat chat = JSON.parseObject(jsonStr, Chat.class);
		sendMessageToOne(chat.getSender(), Chat.jsonStr(chat.getSender(), chat.getReceiver(),
				new Timestamp(System.currentTimeMillis()), chat.getContent(), onlineSessions.size()));
		if (onlineSessions.get(chat.getReceiver()) != null)
			sendMessageToOne(chat.getReceiver(), Chat.jsonStr(chat.getSender(), chat.getReceiver(),
					new Timestamp(System.currentTimeMillis()), chat.getContent(), onlineSessions.size()));
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
					session.getBasicRemote().sendText(chat);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
