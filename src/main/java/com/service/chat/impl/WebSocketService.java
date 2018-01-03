/**
 * 
 */
package com.service.chat.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.BaseDao;
import com.base.entity.Page;
import com.dao.chat.OneToOneMsgDao;
import com.dao.common.MemberDao;
import com.entity.chat.OneToOneMsg;
import com.exception.ChatException;
import com.service.chat.WebSocketServiceInterface;
import com.utils.WebSocketUtils;

/**
 * @author bin
 *
 */
@Service
public class WebSocketService extends BaseDao<OneToOneMsg, Long> implements WebSocketServiceInterface {
	@Autowired
	private OneToOneMsgDao dao;
	@Autowired
	private MemberDao memberDao;

	@Override
	public void updateChatTx(OneToOneMsg msg, Map<String, Object> data)throws ChatException, Exception {
		// 保存聊天数据
 		this.save(msg);
	}

	@Override
	public List<OneToOneMsg> getOneMsg(Page page,long memberId, long firendMemberId)throws ChatException, Exception {
		//验证是否好友
		boolean isFirend = memberDao.isFirend(memberId, firendMemberId);
		if(!isFirend){
			throw new ChatException("对方不是你的好友！请刷新页面再试");
		}
		return WebSocketUtils.orderOneToOneMsg(dao.getOneMsg(page,memberId,firendMemberId));
	}

	@Override
	public void test() {
		dao.test();
	}
}
