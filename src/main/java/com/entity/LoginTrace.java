package com.entity;

import com.base.entity.BaseIdNative;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="DB_LOGIN_TRACE")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class LoginTrace extends BaseIdNative implements Serializable{
  private static final long serialVersionUID = 364787537836839617L;

  @Column
  private long memberId;

  @Column(length=50)
  private String url;

  @Column(length=16)
  private String loginIp;
  /**
  * 这次操作的动作
 */
  @Column(length=255)
  private String action;

  public LoginTrace(long memberId, String url, String loginIp,String action){
    this.memberId = memberId;
    this.url = url;
    this.loginIp = loginIp;
    this.action = action;
  }
  public LoginTrace() {
  }

  public String getLoginIp() {
    return this.loginIp;
  }
  public void setLoginIp(String loginIp) {
    this.loginIp = loginIp;
  }
  public long getMemberId() {
    return this.memberId;
  }
  public void setMemberId(long memberId) {
    this.memberId = memberId;
  }
  public String getUrl() {
    return this.url;
  }
  public void setUrl(String url) {
    this.url = url;
  }

  public String toString() {
    return "LoginTrace [memberId=" + this.memberId + ", url=" + this.url + 
      ", loginIp=" + this.loginIp + "]";
  }
public String getAction() {
	return action;
}
public void setAction(String action) {
	this.action = action;
}
}