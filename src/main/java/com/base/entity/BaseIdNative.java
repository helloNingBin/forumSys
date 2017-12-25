package com.base.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public class BaseIdNative implements Serializable{
  private static final long serialVersionUID = 3119938884093431327L;

  @Id
  @GeneratedValue(generator="paymentableGenerator")
  @GenericGenerator(name="paymentableGenerator", strategy="native")
  protected long id;

  @Column
  @Temporal(TemporalType.TIMESTAMP)
  protected Date lastModTime;
  @Column
  @Temporal(TemporalType.TIMESTAMP)
  protected Date createDate;
  
  public Date getLastModTime()
  {
    return this.lastModTime;
  }

  public void setLastModTime(Date lastModTime) {
    this.lastModTime = lastModTime;
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

public Date getCreateDate() {
	return createDate;
}

public void setCreateDate(Date createDate) {
	this.createDate = createDate;
}
}