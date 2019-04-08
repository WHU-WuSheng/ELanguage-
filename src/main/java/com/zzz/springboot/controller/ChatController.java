package com.zzz.springboot.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzz.springboot.entity.Chat;
import com.zzz.springboot.entity.User;
import com.zzz.springboot.service.IChatService;

@Controller
@RequestMapping("/chat")
public class ChatController {
	@Autowired
	private IChatService iChatService;

	@RequestMapping("test")
	public String test() throws Exception {
		System.out.println(this.iChatService.selectContact("2016302580092"));
		return "login";
	}

	@RequestMapping("show/{username}")
	public String show(@PathVariable("username") String username, ModelMap modelMap, HttpSession session)
			throws Exception {
		User user = (User) session.getAttribute("user");
		List<String> contacts = this.iChatService.selectContact(user.getUsername());
		List<Chat>[] chats = new ArrayList[contacts.size()];
		for (int i = 0; i < contacts.size(); i++) {
			chats[i] = this.iChatService.select(user.getUsername(), username);
		}
		if (!contacts.contains(username)) {
			modelMap.put("newContact", username);
		}
		modelMap.put("chats", chats);
		return "chat";
	}
}
