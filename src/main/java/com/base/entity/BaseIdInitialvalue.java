package com.base.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class BaseIdInitialvalue implements Serializable{
  private static final long serialVersionUID = -7327645231315600539L;

  @Id
  @GeneratedValue(strategy=GenerationType.TABLE, generator="id_gen")
  @TableGenerator(name="id_gen", table="id_gen", initialValue=1000)
  protected long id;

  @Column
  @Temporal(TemporalType.TIMESTAMP)
  protected Date lastModTime;
  @Column
  @Temporal(TemporalType.TIMESTAMP)
  protected Date createDate;
  public long getId(){
    return this.id;
  }

  public void setId(long id) {
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