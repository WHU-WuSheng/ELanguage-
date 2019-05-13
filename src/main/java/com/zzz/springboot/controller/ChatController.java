package com.zzz.springboot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.mail.handlers.image_gif;
import com.zzz.springboot.dao.IUserMapper;
import com.zzz.springboot.entity.Chat;
import com.zzz.springboot.entity.NewInfo;
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

	@RequestMapping("show")
	public String show(String username, ModelMap modelMap, HttpSession session) throws Exception {
		Map map= new HashMap(); 
		User user = (User) session.getAttribute("user");
		List<String> contacts = this.iChatService.selectContact(user.getUsername());
		modelMap.put("contacts", contacts);
		System.out.println(contacts);
//		if (!contacts.contains(username) && this.iUserService.getAllUsername().contains(username)
//				&& !user.getUsername().equals(username)) {
//			modelMap.put("newContact", username);
//		} else if (contacts.contains(username)) {
//			modelMap.put("newContact", username);
//			contacts.remove(username);
//		}
		List<NewInfo> newInfos=this.iChatService.selectNewInfos(user.getUsername());
		
		for(NewInfo temp : newInfos) {
			map.put(temp.getUsername(), temp.getCount());
		}
		for(String temp:contacts) {
			if(!map.containsKey(temp))
				map.put(temp, 0);
		}
		modelMap.addAttribute("map", map);
		return "chat";
	}

	@PostMapping("get")
	@ResponseBody
	public List<Chat> getChat(String username, HttpSession session) throws Exception {
		User user = (User) session.getAttribute("user");
		List<Chat> chats = this.iChatService.select(user.getUsername(), username);
		return chats;
	}
	
	@PostMapping("hasRead")
	@ResponseBody
	public String hasRead(String username, HttpSession session) throws Exception {
		System.out.println("caonimade");
		User user = (User) session.getAttribute("user");
		this.iChatService.hasRead(username,user.getUsername());
		int newCount=0;
		List<NewInfo> newInfos=iChatService.selectNewInfos(user.getUsername());
		for(NewInfo newInfo:newInfos) {
			newCount+=newInfo.getCount();
		}
		session.setAttribute("newCount", newCount);
		return null;
	}
	@GetMapping("getNewCount")
	@ResponseBody
	public int getNewCount(HttpSession session) throws Exception {
		User user = (User) session.getAttribute("user");
//		this.iChatService.hasRead(username,user.getUsername());
		int newCount=0;
		List<NewInfo> newInfos=iChatService.selectNewInfos(user.getUsername());
		for(NewInfo newInfo:newInfos) {
			newCount+=newInfo.getCount();
		}
		return newCount;
	}
}
