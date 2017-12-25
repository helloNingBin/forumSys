/**
 * 
 */
package com.service.chat;

import java.util.List;
import java.util.Map;

import com.base.entity.Page;
import com.base.service.BaseServiceInterface;
import com.entity.chat.OneToOneMsg;
import com.exception.ChatException;

/**
 * @author bin
 *
 */
public interface WebSocketServiceInterface extends BaseServiceInterface<OneToOneMsg, Long> {
	/**
	 * 因为事务只能控制在一个service方法里，所以需要把所有业务都放在该方法中，data是需要处理的数据
	 * @param msg              信息
	 * @param data             用map封装要处理的数据  
	 * @throws ChatException   抛业务异常
	 * @throws Exception       抛运行异常
	 */
	void updateChatTx(OneToOneMsg msg,Map<String, Object> data)throws ChatException,Exception;
	/**
	 * 获取记录
	 * @param memberId       登录会员id
	 * @param firendMemberId 好友会员id
	 * @return 聊天记录
	 */
	List<OneToOneMsg> getOneMsg(Page page,long memberId,long firendMemberId)throws ChatException,Exception;
}
