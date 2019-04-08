package com.zzz.springboot.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * <p>Title: CustomExceptionResolver</p>
 * <p>Description: </p>
 * <p>Company: com.zzz</p>
 * @author 张志铮
 * @date 2019年3月28日 下午1:56:33
 */
@ControllerAdvice
public class MyExceptionResolver {
	@ExceptionHandler(Exception.class)
	public String exception(Exception exception, Model model) {
		CustomException customException = null;
		if (exception instanceof CustomException) {
			customException = (CustomException) exception;
		} else {
			exception.printStackTrace();
			customException = new CustomException("未知错误");
		}
		String message = customException.getMessage();
		model.addAttribute("message", message);
		return "error";
	}
}
