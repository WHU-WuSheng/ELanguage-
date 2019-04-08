package com.zzz.springboot.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zzz.springboot.entity.User;
import com.zzz.springboot.service.IUserService;

import javassist.expr.NewArray;
import net.bytebuddy.asm.Advice.This;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService iUserService;

	@RequestMapping(value = "show/{username}")
	public String show(@PathVariable("username") String username, ModelMap modelMap) throws Exception {
		modelMap.put("selectUser", this.iUserService.selectUserByUsername(username));
		return "user";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(String user, String password, String url, HttpServletRequest request) throws Exception {
		User user2 = iUserService.login(user, password);
		if (user2 == null) {
			return "forward:/login";
		}
		HttpSession session = request.getSession();
		session.setAttribute("user", user2);
		session.setMaxInactiveInterval(-1);
		if (url == null || "".equals(url))
			return "redirect:/";
		else {
			return "redirect:" + url;
		}
	}

	@RequestMapping(value = "logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request) throws Exception {
		HttpSession session=request.getSession();
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public String modify(User user, ModelMap modelMap) throws Exception {
		iUserService.modify(user);
		modelMap.put("user", user);
		return "info";
	}

	@RequestMapping(value = "selectUserByCredit", method = RequestMethod.POST)
	public String selectUserByCredit(int credit, ModelMap modelMap) throws Exception {
		modelMap.put("result", iUserService.selectUserByCredit(credit));
		return "main";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(User user, MultipartFile pic, ModelMap modelMap) throws Exception {
		String picName = "default";
		String extName = ".jpg";
		if (pic != null) {
			String oriName = pic.getOriginalFilename();
			if (oriName != null && oriName.length() > 0) {
				// 把图片保存到图片目录下
				// 保存图片，这个图片有的时候文件名可能会重复，你保存多了会把原来的图片给覆盖掉，这就不太合适了。
				// 所以为每个文件生成一个新的文件名
				picName = UUID.randomUUID().toString();
				// 截取文件的扩展名(如.jpg)
				extName = oriName.substring(oriName.lastIndexOf("."));
				// 保存文件
				pic.transferTo(new File("D:/upload/pic/" + picName + extName));
			}
		}
		user.setPicture(picName + extName);
		if (user.getProfile() == null || "".equals(user.getProfile()))
			user.setProfile("这个家伙很懒，什么都没留下");
		iUserService.register(user);
		modelMap.put("success", "注册成功");
		modelMap.put("view", "登录");
		modelMap.put("url", "/login");
		return "success";
	}

	@RequestMapping(value = "loginByEmailAndICode", method = RequestMethod.POST)
	public String loginByEmailAndICode(String email, ModelMap modelMap) throws Exception {
		User user = iUserService.selectUserByEmail(email);
		modelMap.put("user", user);
		return "main";
	}

	@RequestMapping(value = "loginByPhoneAndICode", method = RequestMethod.POST)
	public String loginByPhoneAndICode(String phone, ModelMap modelMap) throws Exception {
		User user = iUserService.selectUserByPhone(phone);
		modelMap.put("user", user);
		return "main";
	}

	@RequestMapping(value = "selectUserByUsername", method = RequestMethod.POST)
	public String selectUserByUsername(String username, ModelMap modelMap) throws Exception {
		User user = iUserService.selectUserByUsername(username);
		modelMap.put("user", user);
		return "info";
	}
}
