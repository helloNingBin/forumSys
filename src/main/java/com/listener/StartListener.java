package com.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.utils.CommConstant;

public class StartListener implements ServletContextListener{
  public void contextDestroyed(ServletContextEvent arg0){
  }

  public void contextInitialized(ServletContextEvent servletContextEvent){
    String contextPath = servletContextEvent.getServletContext().getContextPath() + "/";
    servletContextEvent.getServletContext().setAttribute(CommConstant.REQUEST_BASE_PATH, contextPath);
  }
}