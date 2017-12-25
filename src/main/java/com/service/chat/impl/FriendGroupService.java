/**
 * 
 */
package com.service.chat.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.impl.BaseService;
import com.dao.chat.FriendGroupDao;
import com.entity.Member;
import com.entity.chat.FriendGroup;
import com.service.chat.FriendGroupServiceInterface;

/**
 * @author bin
 *
 */
@Service
public class FriendGroupService extends BaseService<FriendGroup, Long> implements FriendGroupServiceInterface{
    @Autowired
    private FriendGroupDao friendGroupDao;
	@Override
	public List<FriendGroup> getfriendGroupList(long memberId) throws Exception {
		return friendGroupDao.getfriendGroupList(memberId);
	}

}
