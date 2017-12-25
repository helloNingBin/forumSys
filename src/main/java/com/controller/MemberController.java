package com.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.controller.BaseController;
import com.base.exception.RegisterException;
import com.entity.Member;
import com.service.member.MemberServiceInterface;


/**
 * @author bin
 *2017.12.03
 *会员
 */
@Controller
public class MemberController extends BaseController{
   private static Logger log = Logger.getLogger(MemberController.class);
   @Autowired
   private MemberServiceInterface memberServiceInterface;
   /**
   * @return
   */
   @RequestMapping("/register")
   @ResponseBody
   public Map<String,Object> register(Member member){
	   Map<String,Object> data = new HashMap<String, Object>();
	   boolean result = false;
	   String msg = "注册失败";
	   try {
		   memberServiceInterface.saveMember(member);
		   result = true;
		   msg = "注册成功";
	   }catch(RegisterException e){
		   msg = e.getMessage();
	   } catch (Exception e) {
	 	  log.error("注册异常：" + e.getMessage(), e);
	   }
	   data.put("result", result);
	   data.put("msg", msg);
	   return data;
   }
}
