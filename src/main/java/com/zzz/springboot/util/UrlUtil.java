package com.zzz.springboot.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Component
public class UrlUtil {
	@Autowired
	private WebApplicationContext webApplicationContext;

	public List<String> getAllUrl() {
		RequestMappingHandlerMapping requestMappingHandlerMapping = this.webApplicationContext
				.getBean(RequestMappingHandlerMapping.class);
		//获取url与类和方法的对应信息
		Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
		List<String> list = new ArrayList<>();
		for (RequestMappingInfo requestMappingInfo : map.keySet()) {
			//获取url的Set集合，一个方法可能对应多个url
			Set<String> patterns = requestMappingInfo.getPatternsCondition().getPatterns();
			for (String url : patterns) {
				list.add(url);
			}
		}
		return list;
	}
}
