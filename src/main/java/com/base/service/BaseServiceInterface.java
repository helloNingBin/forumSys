package com.base.service;

import java.io.Serializable;

import com.base.dao.IBaseDao;

public interface BaseServiceInterface<T extends Serializable, PK extends Serializable> extends IBaseDao<T, PK>{
}
