package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.controller.BaseController;
import com.entity.Member;
import com.entity.chat.FriendGroup;
import com.entity.chat.FriendsInGroup;
import com.service.chat.FriendGroupServiceInterface;
import com.service.chat.FriendsInGroupServiceInterface;
import com.service.member.MemberServiceInterface;
import com.utils.CommConstant;

/**
 * @author bin
 *2017.12.02
 *登录
 */
@Controller
public class LoginController extends BaseController{
	private static Logger log = Logger.getLogger(LoginController.class);
	@Autowired
	private MemberServiceInterface memberServiceInterface;
	@Autowired
	private FriendGroupServiceInterface friendGroupService;
	@Autowired
	private FriendsInGroupServiceInterface friendsInGroupService;
   /**
   * 去登录页面
   */
   @RequestMapping("/toLogin")
   public String toLogin(){
	   return "/chat/login";
   }

   /**
   * 去注册页面
   */
   @RequestMapping("/toRegister")
   public String toRegister(){
	   return "/chat/register";
   }

	/**
	 * 去主页
	 */
	@RequestMapping("/v/toIndex")
	public String toIndex(Model model) {
		try {
			long memberId = Long.valueOf(super.getLoginMemberId());
			List<FriendGroup> friendGroupList = friendGroupService.getfriendGroupList(memberId);
			List<FriendsInGroup> friendsInGroupList = friendsInGroupService.getFriendsInGroupList(friendGroupList);
			model.addAttribute("friendGroupList", friendGroupList);
			model.addAttribute("friendsInGroupList", friendsInGroupList);
		} catch (Exception e) {
			 log.error(e.getMessage(), e);
		}
		return "/chat/index";
	}

	/**
	 * 会员登录
	 * @param session  当前session
	 * @param phone    登录账号
	 * @param loginPwd 登录密码
	 * @return
	 */
    @RequestMapping("/login")
    @ResponseBody
	public Map<String, Object> login(HttpServletRequest request,HttpSession session, String phone,String loginPwd) {
	   Map<String,Object> data = new HashMap<String, Object>();
	   boolean result = false;
	   String msg = "登录失败";	   
	   try {
		   Member member = memberServiceInterface.checkLogin(phone, loginPwd);
		   if(member == null){
			   msg = "账号或密码不正确";
		   }else{
			   result = true;
			   //保存Member对象和其id
			   session.setAttribute(CommConstant.LOGIN_MEMBER, member);
			   session.setAttribute(CommConstant.LOGIN_MEMBER_ID_SESSION_KEY, member.getId());
			   //去除转跳URL
			   if(session.getAttribute(CommConstant.JUMP_URL) != null){
//				   request.getRequestDispatcher(session.getAttribute(CommConstant.JUMP_URL).toString()).forward(request, response);
			   }
			   session.removeAttribute(CommConstant.JUMP_URL);
		   }
	   } catch (Exception e) {
		  log.error(e.getMessage(), e);
	   }
	   data.put("result", result);
	   data.put("msg", msg);
	   return data;
   }
}
