/**
 * 
 */
package com.controller.chat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.controller.BaseController;
import com.base.entity.Page;
import com.entity.Member;
import com.entity.chat.OneToOneMsg;
import com.exception.ChatException;
import com.service.chat.WebSocketServiceInterface;
import com.service.member.MemberServiceInterface;
import com.utils.CommConstant;

/**
 * 聊天信息
 * @author bin
 *
 */
@Controller
@RequestMapping(value="/v/one")
public class ChatController extends BaseController{
	private static Logger log = Logger.getLogger(ChatController.class);
	@Autowired
	private WebSocketServiceInterface webService;
	@Autowired
	private MemberServiceInterface memberService;
	
	@RequestMapping(value="/getMsg")
	@ResponseBody
	public Map<String,Object> getOneToOneMsg(long firendMemberId,Long minId){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("result", false);
		try {
			Page page = new Page(0, CommConstant.MAX_MSG_LENGTH);
			if(minId != null){
				page.setWhereClause(" and id < " + minId);
			}
			List<OneToOneMsg> msgList = webService.getOneMsg(page,super.getLoginMember().getId(), firendMemberId);
			map.put("msgList", msgList);
			map.put("result", true);
		} catch (ChatException e) {
			map.put("msg", e.getMessage());
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			map.put("msg", "网络异常，请稍后再试");
			log.error(e.getMessage(), e);
		}
		
		return map;
	}
	@RequestMapping("/toChatWindow")
	public String toChatWindow(Model model,long toMemberId){
		try {
			Member member = memberService.get(toMemberId);
			model.addAttribute("toMember", member);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			return null;
		}
		return "chat/talkWindow";
	}
}
