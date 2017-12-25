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
public class BaseIdUUID
  implements Serializable
{
  private static final long serialVersionUID = 2902726770754629951L;

  @Id
  @GeneratedValue(generator="system-uuid")
  @GenericGenerator(name="system-uuid", strategy="uuid")
  @Column(length=32)
  protected String id;

  @Column
  @Temporal(TemporalType.TIMESTAMP)
  protected Date lastModTime;
  @Column
  @Temporal(TemporalType.TIMESTAMP)
  protected Date createDate;
  public String getId()
  {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Date getLastModTime() {
    return this.lastModTime;
  }

  public void setLastModTime(Date lastModTime) {
    this.lastModTime = lastModTime;
  }

public Date getCreateDate() {
	return createDate;
}

public void setCreateDate(Date createDate) {
	this.createDate = createDate;
}
}