package com.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.entity.Member;
import com.utils.CommConstant;

public class ValidateInterceptor extends HandlerInterceptorAdapter{
  private static Logger log = Logger.getLogger(ValidateInterceptor.class);

  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{
	String url = request.getRequestURI().substring(request.getContextPath().length());
	String query = request.getQueryString();
	Member member = (Member) request.getSession().getAttribute(CommConstant.LOGIN_MEMBER);
	if(member == null){
		//记录登录前的请求地址
		if (StringUtils.isNotBlank(query)){
			url += "?" + query;
		}
		request.getSession().setAttribute(CommConstant.JUMP_URL, url);
		request.getRequestDispatcher("/toLogin.do").forward(request, response);
		return false;
	}
    return true;
  }

  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)throws Exception{
  }

  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)throws Exception{
  }
}