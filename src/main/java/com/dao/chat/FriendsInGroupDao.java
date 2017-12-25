/**
 * 
 */
package com.dao.chat;

import java.util.List;

import org.hibernate.Query;
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
public class FriendsInGroupDao extends BaseDao<FriendGroup, Long> implements IBaseDao<FriendGroup, Long> {

	/**
	 * @param groupIds
	 * @return
	 */
	public List<FriendsInGroup> getFriendsInGroupList(Long[] groupIds) {
		String hql = "from FriendsInGroup where firendGroupId in(:ids)";
		Query query = super.getSession().createQuery(hql);
		query.setParameterList("ids", groupIds);
		return query.list();
	}

}
