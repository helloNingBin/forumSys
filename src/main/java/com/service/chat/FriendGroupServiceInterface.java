/**
 * 
 */
package com.service.chat;

import java.util.List;

import com.base.service.BaseServiceInterface;
import com.entity.chat.FriendGroup;

/**
 * @author bin
 *
 */
public interface FriendGroupServiceInterface extends BaseServiceInterface<FriendGroup,Long> {
	 /**
	  * 查找会员的所有分组
	 * @param MemberId 会员id
	 * @return
	 * @throws Exception
	 */
	List<FriendGroup> getfriendGroupList(long memberId)throws Exception;
}
