package com.base.service.impl;

import java.io.Serializable;

import com.base.dao.BaseDao;
import com.base.service.BaseServiceInterface;

public class BaseService<T extends Serializable,PK extends Serializable> extends BaseDao<T, PK> implements BaseServiceInterface<T, PK> {

}
