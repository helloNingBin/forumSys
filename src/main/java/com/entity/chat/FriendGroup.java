/**
 * 
 */
package com.entity.chat;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.base.entity.BaseIdNative;

/** 好友分组
 * 
 * 2017.11.27
 * bin.ning
 *
 */
@Entity
@Table(name="DB_FRIENDGROUP")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class FriendGroup extends BaseIdNative implements Serializable{
	private static final long serialVersionUID = 1L;
	   /** 
	    * 所属会员
	    */
	   @Column
	   private long memberId;
	   /** 
	    * 组别名称
	   */
	   @Column(length=20)
	   private String groupName;
	   /** 
	    * 组员人数
	   */
	   @Column
	   private int friendCount;
	   /**
	    *  在线人数
	   */
	   @Column
	   private int inlineCount;
	public long getMemberId() {
		return memberId;
	}
	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getFriendCount() {
		return friendCount;
	}
	public void setFriendCount(int friendCount) {
		this.friendCount = friendCount;
	}
	public int getInlineCount() {
		return inlineCount;
	}
	public void setInlineCount(int inlineCount) {
		this.inlineCount = inlineCount;
	}
	@Override
	public String toString() {
		return "FriendGroup [memberId=" + memberId + ", groupName=" + groupName
				+ ", friendCount=" + friendCount + ", inlineCount="
				+ inlineCount + ", id=" + id + ", lastModTime=" + lastModTime
				+ ", createDate=" + createDate + "]";
	}
}
