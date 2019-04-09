package com.zzz.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zzz.springboot.interceptor.LoginInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Autowired
	private LoginInterceptor loginInterceptor;

	/* （非 Javadoc）
	 * <p>Title: addInterceptors</p>
	 * <p>Description: </p>
	 * @param registry
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry)
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// springboot默认有/error
		registry.addInterceptor(this.loginInterceptor).addPathPatterns("/**").excludePathPatterns(/*"/static/**",*/ "/",
				"/login", "/register", "/download/*", "/loginInterceptor", "/user/login", "/user/register",
				"/user/logout", "/user/show/*", "/capability/show/*", "/error");
		WebMvcConfigurer.super.addInterceptors(registry);
	}

	/* （非 Javadoc）
	 * <p>Title: addResourceHandlers</p>
	 * <p>Description: </p>
	 * @param registry
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO 自动生成的方法存根
		registry.addResourceHandler("/pic/**").addResourceLocations("file:D:/upload/pic/");
		//registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}

}
