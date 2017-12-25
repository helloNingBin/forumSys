/**
 * 
 */
package com.service.chat.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.impl.BaseService;
import com.dao.chat.FriendsInGroupDao;
import com.entity.chat.FriendGroup;
import com.entity.chat.FriendsInGroup;
import com.service.chat.FriendsInGroupServiceInterface;

/**
 * @author bin
 *
 */
@Service
public class FriendsInGroupService extends BaseService<FriendsInGroup, Long> implements FriendsInGroupServiceInterface{
    @Autowired
    private FriendsInGroupDao dao;
	@Override
	public List<FriendsInGroup> getFriendsInGroupList(List<FriendGroup> list)throws Exception {
		if(list == null || list.size() == 0){
			return null;
		}
		int len = list.size();
		Long[] groupIds = new Long[len];
		for(int i = 0;i < len;i++){
			groupIds[i] = list.get(i).getId();
		}
		return dao.getFriendsInGroupList(groupIds);
	}

}
