/**
 * 
 */
package com.service.chat;

import java.util.List;

import com.base.service.BaseServiceInterface;
import com.entity.chat.FriendGroup;
import com.entity.chat.FriendsInGroup;

/**
 * @author bin
 *
 */
public interface FriendsInGroupServiceInterface extends BaseServiceInterface<FriendsInGroup,Long> {
    List<FriendsInGroup> getFriendsInGroupList(List<FriendGroup> list)throws Exception;
}
