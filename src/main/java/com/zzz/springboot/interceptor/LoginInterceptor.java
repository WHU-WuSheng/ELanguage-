package com.zzz.springboot.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.zzz.springboot.entity.User;
import com.zzz.springboot.util.StringToTimestampConverter;
import com.zzz.springboot.util.UrlUtil;

/**
 * <p>Title: LoginInterceptor</p>
 * <p>Description: </p>
 * <p>Company: com.zzz</p>
 * @author 张志铮
 * @date 2019年3月28日 下午1:56:25
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
	@Autowired
	private UrlUtil urlUtil;

	
	
	
	/* （非 Javadoc）
	 * <p>Title: preHandle</p>
	 * <p>Description: </p>
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURI();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			return true;
		} else {
			//判断url是否是本项目中的url
			if (this.urlUtil.getAllUrl().contains(url)) {
				System.out.println(url);
				request.setAttribute("url", url);
				//loginInterceptor自身不能被拦截
				request.getRequestDispatcher("/loginInterceptor").forward(request, response);
				return false;
			}
			return true;
		}
		
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO 自动生成的方法存根

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO 自动生成的方法存根

	}

}
