package com.controller.test;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.chat.OneToOneMsgDao;
import com.exception.ChatException;
import com.service.chat.WebSocketServiceInterface;

@Controller
public class TestController
{
  private static final Logger logger = Logger.getLogger(TestController.class);
  @Autowired
  private WebSocketServiceInterface wsi;

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
  @RequestMapping("/cacheTest")
  @ResponseBody
  public String cacheTest(){
	  wsi.test();
	  return "abc";
  }
  @RequestMapping("/updateTest")
  @ResponseBody
  public String updateTest() throws ChatException, Exception{
	  wsi.updateChatTx(null, null);
	  return "abc";
  }
  public String getUrl() {
    return this.url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
}