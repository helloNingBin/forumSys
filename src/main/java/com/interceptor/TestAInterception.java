package com.interceptor;

import com.controller.test.TestController;
import java.io.PrintStream;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TestAInterception extends HandlerInterceptorAdapter
{
  private static final Logger logger = Logger.getLogger(TestAInterception.class);

  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
  {
    HandlerMethod handlerMethod = (HandlerMethod)handler;
    TestController bean = (TestController)handlerMethod.getBean();
    RequestMapping requestMapping = (RequestMapping)handlerMethod.getMethodAnnotation(RequestMapping.class);
    System.out.println("requestMapping:" + Arrays.toString(requestMapping.value()));
    logger.info("preHandle:" + bean.getUrl());
    return super.preHandle(request, response, handler);
  }

  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
  {
    logger.info("postHandle");
    super.postHandle(request, response, handler, modelAndView);
  }

  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
  {
    logger.info("afterCompletion");
    super.afterCompletion(request, response, handler, ex);
  }

  public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
  {
    logger.info("afterConcurrentHandlingStarted");
    super.afterConcurrentHandlingStarted(request, response, handler);
  }
}