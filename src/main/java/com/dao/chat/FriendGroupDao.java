/**
 * 
 */
package com.dao.chat;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.dao.IBaseDao;
import com.entity.chat.FriendGroup;
import com.entity.chat.FriendsInGroup;

/**
 * @author bin
 *
 */
@Repository
public class FriendGroupDao extends BaseDao<FriendGroup, Long> implements IBaseDao<FriendGroup, Long> {
//	listByProperty
	public List<FriendGroup> getfriendGroupList(long memberId)throws Exception{
		return super.listByProperty("memberId", memberId);
	}
}
