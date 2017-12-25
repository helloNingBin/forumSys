package com.controller.test;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController
{
  private static final Logger logger = Logger.getLogger(TestController.class);

  @Value("${myPhone}")
  private String url;

  @RequestMapping({"/test/testRequest"})
  @ResponseBody
  public String testRequest(String msg, Long userId)
  {
    return this.url;
  }
  @RequestMapping("/test")
  public String test(Long id){
	  System.out.println(id);
	  return "chat/talkWindow";
  }
  public String getUrl() {
    return this.url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
}