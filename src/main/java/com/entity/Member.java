package com.entity;

import java.util.Date;

import com.base.entity.BaseIdInitialvalue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
/** 前端登录会员
 * 
 * 2017.11.26
 * binbin.ning
 * v1
 */  
@Entity
@Table(name="DB_MEMBER")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Member extends BaseIdInitialvalue{
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 昵称*/
	   @Column(length=50)
	   private String nickName;
	   /** 姓名 */
	   @Column(length=50)
	   private String name;
	   /** 性别 */
	   @Column
	   private int gender;
	   /** 家庭地址 */
	   @Column(length=150)
	   private String homeAddress;
	   /** 生日 */
	   @Column
	   private Date birDate;
	   /** 工作 */
	   @Column(length=50)
	   private String job;
	   /** 电话 */
	   @Column(length=50)
	   private String phone;
	   /** 登录密码 */
	   @Column(length=50)
	   private String loginPwd;
	   /** 个性签名 */
	   @Column(length=250)
	   private String personSign;
	   /** 头像图片 */
	   @Column(length=250)
	   private String personImg;
	   /** 微信 */
	   @Column(length=250)
	   private String weChat;
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public Date getBirDate() {
		return birDate;
	}
	public void setBirDate(Date birDate) {
		this.birDate = birDate;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getPersonSign() {
		return personSign;
	}
	public void setPersonSign(String personSign) {
		this.personSign = personSign;
	}
	public String getPersonImg() {
		return personImg;
	}
	public void setPersonImg(String personImg) {
		this.personImg = personImg;
	}
	public String getWeChat() {
		return weChat;
	}
	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}
	   
}