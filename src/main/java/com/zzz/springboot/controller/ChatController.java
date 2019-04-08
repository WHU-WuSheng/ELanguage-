package com.zzz.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzz.springboot.service.IChatService;

@Controller
@RequestMapping("/chat")
public class ChatController {
	@Autowired
	private IChatService iChatService;
}
