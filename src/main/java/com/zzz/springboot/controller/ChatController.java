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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.mail.handlers.image_gif;
import com.zzz.springboot.dao.IUserMapper;
import com.zzz.springboot.entity.Chat;
import com.zzz.springboot.entity.User;
import com.zzz.springboot.service.IChatService;
import com.zzz.springboot.service.IUserService;

@Controller
@RequestMapping("/chat")
public class ChatController {
	@Autowired
	private IChatService iChatService;
	@Autowired
	private IUserService iUserService;

	@RequestMapping("test")
	public String test() throws Exception {
		System.out.println(this.iChatService.selectContact("2016302580092"));
		return "login";
	}

	@RequestMapping("show")
	public String show(String username, ModelMap modelMap, HttpSession session) throws Exception {
		User user = (User) session.getAttribute("user");
		List<String> contacts = this.iChatService.selectContact(user.getUsername());
		if (!contacts.contains(username) && this.iUserService.getAllUsername().contains(username)) {
			modelMap.put("newContact", username);
		}
		modelMap.put("contacts", contacts);
		return "chat";
	}

	@PostMapping("get")
	@ResponseBody
	public List<Chat> getChat(String username, HttpSession session) throws Exception {
		User user = (User) session.getAttribute("user");
		List<Chat> chats = this.iChatService.select(user.getUsername(), username);
		return chats;
	}
}
