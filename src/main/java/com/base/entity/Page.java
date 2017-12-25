package com.base.entity;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Page
  implements Serializable
{
  private static final long serialVersionUID = 364787537836839617L;
  private Integer start;
  private Long total;
  private Integer limit;
  private Integer pageCount = Integer.valueOf(0);
  private Integer pageNumber = Integer.valueOf(0);
  private List<?> rows;
  private String whereClause = StringUtils.EMPTY;

  public Page()
  {
  }

  public Page(Integer start, Integer limit)
  {
    this.start = start;
    this.limit = limit;
  }

  public Long getTotal() {
    return this.total;
  }

  public void setTotal(Long total) {
    this.total = total;
  }

  public List<?> getRows() {
    return this.rows;
  }

  public void setRows(List<?> rows) {
    this.rows = rows;
  }

  public Integer getStart() {
    return this.start;
  }

  public void setStart(Integer start) {
    this.start = start;
  }

  public Integer getLimit() {
    return this.limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public Integer getPageCount() {
    long t = 0L;
    if (getTotal() != null) {
      t = getTotal().longValue();
    }
    long l = 0L;
    if (getLimit() != null) {
      l = getLimit().intValue();
    }
    if (l == 0L) {
      return Integer.valueOf(0);
    }
    this.pageCount = Integer.valueOf((int)(t / l));
    int m = (int)(t % l);
    if (m > 0) {
      this.pageCount = Integer.valueOf(this.pageCount.intValue() + 1);
    }
    return this.pageCount;
  }

  public Integer getPageNumber() {
    if ((getStart() != null) && (getLimit() != null)) {
      if (getStart().intValue() == 0)
        this.pageNumber = Integer.valueOf(1);
      else {
        this.pageNumber = Integer.valueOf(getStart().intValue() / getLimit().intValue() + 1);
      }
    }
    return Integer.valueOf(this.pageNumber.intValue() == 0 ? 1 : this.pageNumber.intValue());
  }

  public void setPageNumber(Integer pageNumber) {
    if (pageNumber == null) {
      return;
    }
    this.pageNumber = pageNumber;
  }

  public String getWhereClause() {
    return this.whereClause;
  }

  public void setWhereClause(String whereClause) {
    this.whereClause = whereClause;
  }
}