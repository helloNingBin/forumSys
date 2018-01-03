package com.base.interceptor;

import com.entity.LoginTrace;
import com.service.member.MemberServiceInterface;
import com.utils.HttpUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginTraceInterceptor extends HandlerInterceptorAdapter{
  private static Logger log = Logger.getLogger(LoginTraceInterceptor.class);

  @Autowired
  private MemberServiceInterface memberServiceInterface;
  /* 
  *拦截器访问顺序
  *preHandle--->method--->postHandle--->afterCompletion
  */
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{
    String requestUri = request.getRequestURI();//  /forumSys/test/alipay/oneToOne.do
    String contextPath = request.getContextPath();
    String url = requestUri.substring(contextPath.length());
    String loginIp = request.getRemoteAddr();
//    log.info("==============进入LoginTraceInterceptor拦截器================:" + url);
    Long memberId = (Long)request.getSession().getAttribute("LOGIN_MEMBER_ID_SESSION_KEY");
/*    LoginTrace loginTrace = new LoginTrace(memberId == null ? -1L : memberId.longValue(), url, loginIp,HttpUtils.mapToString(request.getParameterMap()));
    memberServiceInterface.saveLogin(loginTrace);*/
    return true;
  }

  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)throws Exception{
//	  System.out.println("LoginTraceInterceptor-------->postHandle");
  }

  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)throws Exception{
//	  System.out.println("afterCompletion-------->postHandle");
  }
}