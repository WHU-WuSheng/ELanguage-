package com.zzz.springboot.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzz.springboot.service.IUserService;

@Controller
public class CustomController {
	@Autowired
	private IUserService iUserService;

	@RequestMapping("/chat2")
	public String chat(Model model, String username) throws Exception {
		model.addAttribute("username", username);
		return "chat2";
	}

	@RequestMapping("/login2")
	public String login2() throws Exception {
		return "login2";
	}

	@RequestMapping("/")
	public String index(Model model) throws Exception {
		model.addAttribute("users", this.iUserService.selectUserByCredit(0));
		return "index";
	}

	@RequestMapping("/login")
	public String login(String url, Model model) throws Exception {
		model.addAttribute("url", url);
		return "login";
	}

	@RequestMapping("/register")
	public String register() throws Exception {
		return "register";
	}

	/**
	 * <p>Title: loginInterceptor</p>
	 * <p>Description: 除success和error外，每个html模板都对应至少一个uri,所有请求转发和重定向都发给相应的uri</p>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/loginInterceptor")
	public String loginInterceptor() throws Exception {
		return "loginInterceptor";
	}

	@PostMapping("/download/{filename}")
	public String loginInterceptor(@PathVariable("filename") String filename, HttpServletResponse response)
			throws Exception {
		filename = URLDecoder.decode(filename, "utf-8");
		// '\\'表示'\'
		filename = filename.replace("\\", "/");
		File file = new File(filename);
		String[] strings = filename.split("/");
		if (file.exists()) { //判断文件文件是否存在
			response.setContentType("application/force-download");
			response.setHeader("Content-Disposition",
					"attachment;fileName=" + URLEncoder.encode(strings[strings.length - 1], "utf-8"));
			byte[] buffer = new byte[1024];
			FileInputStream fis = new FileInputStream(file); //文件输入流
			BufferedInputStream bis = new BufferedInputStream(fis);
			OutputStream os = response.getOutputStream(); //输出流
			int i;
			while ((i = bis.read(buffer)) != -1) {
				os.write(buffer);
			}
			bis.close();
			fis.close();
		}
		return null;
	}
}
