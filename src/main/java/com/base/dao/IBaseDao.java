package com.base.dao;

import java.io.Serializable;
import java.util.List;

public abstract interface IBaseDao<T extends Serializable, PK extends Serializable>
{
  public abstract T get(PK paramPK);

  public abstract List<T> readAll();

  public abstract boolean save(T paramT);

  public abstract boolean update(T paramT);

  public abstract boolean delete(T paramT);

  public abstract boolean saveOrUpdate(T paramT);

  public abstract boolean merge(T paramT);

  public abstract boolean deleteByKey(PK paramPK);

  public abstract boolean deleteByQuery(String paramString);

  public abstract List<T> findBySQL(String paramString);

  public abstract T findUniqueObjectBySQL(String paramString);

  public abstract List<T> findObjectListsByPage(String paramString, int paramInt1, int paramInt2);
}