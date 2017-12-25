/**
 * 
 */
package com.entity.chat;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.base.entity.BaseIdNative;
import com.entity.Member;

/** 
 * 组别中的好友
 * 2017.11.27
 * bin.ning
 *
 */
@Entity
@Table(name="DB_FRIENDSINGROUP")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class FriendsInGroup extends BaseIdNative implements Serializable{
	private static final long serialVersionUID = 1L;
      /**
	  * 组别id
	  */
	  @Column
	  private long firendGroupId;
	  @ManyToOne(fetch=FetchType.LAZY)
	  @JoinColumn(name="firendMemberId",nullable=true,foreignKey=@ForeignKey(name="null"))
	  @NotFound(action=NotFoundAction.IGNORE)
	   private Member firendMember;
	   /** 亲密等级
	    * 
	    */
	  @Column
	   private int closeLevel = 0;
	  /**
	  * 登录会员的id
	  * 这里的字段有点重复，但这字段的内容是不会改变的，会使查询速度加快
	  */
	  @Column
	  private long memberId;
	   /** 
	    * 聊天记录
	   
	  @Column
	   private int chatRecord;*/
	public long getFirendGroupId() {
		return firendGroupId;
	}
	public void setFirendGroupId(long firendGroupId) {
		this.firendGroupId = firendGroupId;
	}
	public Member getFirendMember() {
		return firendMember;
	}
	public void setFirendMember(Member firendMember) {
		this.firendMember = firendMember;
	}
	public int getCloseLevel() {
		return closeLevel;
	}
	public void setCloseLevel(int closeLevel) {
		this.closeLevel = closeLevel;
	}
	@Override
	public String toString() {
		return "FriendsInGroup [firendGroupId=" + firendGroupId
				+ ", firendMember=" + firendMember + ", closeLevel="
				+ closeLevel + ", id=" + id + ", lastModTime=" + lastModTime
				+ ", createDate=" + createDate + "]";
	}
	public long getMemberId() {
		return memberId;
	}
	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}
}
