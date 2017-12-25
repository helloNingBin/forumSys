/**
 * 
 */
package com.entity.chat;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.base.entity.BaseIdNative;
import com.utils.CommConstant;

/**
 * 一对一，个人聊天记录 2017.11.27 bin.ning
 * 
 */
@Entity
@Table(name = "DB_ONETOONEMSG")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class OneToOneMsg extends BaseIdNative implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 发送人id
	 */
	@Column
	private long fromId;
	/**
	 * 接收人id
	 */
	@Column
	private long toId;
	/**
	 * 发送内容
	 * */
	@Column
	private String content;
	/**
	 * 是否发送（0：否；1：）
	 * */
	@Column
	private int isSend;
	/**
	 * 时间标志
	 */
	@Transient
	private String timeRemark;
	@Transient
	private String createDateFormat;

	public long getFromId() {
		return fromId;
	}

	public void setFromId(long fromId) {
		this.fromId = fromId;
	}

	public long getToId() {
		return toId;
	}

	public void setToId(long toId) {
		this.toId = toId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getIsSend() {
		return isSend;
	}

	public void setIsSend(int isSend) {
		this.isSend = isSend;
	}

	public OneToOneMsg(long fromId, long toId, String content,Date createDate) {
		super();
		this.fromId = fromId;
		this.toId = toId;
		this.content = content;
		this.createDate = createDate;
	}
	public OneToOneMsg() {
		super();
	}
	@Override
	public String toString() {
 		return "OneToOneMsg [fromId=" + fromId + ", toId=" + toId
				+ ", content=" + content + ", isSend=" + isSend
				+ ", timeRemark=" + timeRemark + ", createDate=" + (createDate == null ? null : CommConstant.YMDHMS_DATEFORMAT.format(createDate))
				+ "]"; 
	}

	public String getTimeRemark() {
		return timeRemark;
	}

	public void setTimeRemark(String timeRemark) {
		this.timeRemark = timeRemark;
	}

	public String getCreateDateFormat() {
		return CommConstant.YMDHMS_DATEFORMAT.format(createDate);
	}
}
