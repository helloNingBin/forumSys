package com.base.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public abstract class BaseDao<T extends Serializable, PK extends Serializable> extends SessionFactoryBase implements IBaseDao<T, PK> {
	
	private Class<T> entityClass;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BaseDao() {
		this.entityClass = null;
		Class c = getClass();
		Type t = c.getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			this.entityClass = (Class<T>) p[0];
		}
	}

	@SuppressWarnings("unchecked")
	public T get(PK id) {
		return (T) getSession().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> readAll() {
		return getSession().createCriteria(entityClass).list();
	}

	public boolean save(T entity) {
		boolean flag = false;
		try {
			getSession().save(entity);
			flag = true;
		} catch (Exception e) {
			flag = false;
			throw new HibernateException(e);
		}
		return flag;
	}

	public boolean update(T entity) {
		boolean flag = false;
		try {
			getSession().update(entity);
			flag = true;
		} catch (Exception e) {
			flag = false;
			throw new HibernateException(e);
		}
		return flag;
	}

	public boolean delete(T entity) {
		boolean flag = false;
		try {
			getSession().delete(entity);
			flag = true;
		} catch (Exception e) {
			flag = false;
			throw new HibernateException(e);
		}
		return flag;
	}

	public boolean saveOrUpdate(T entity) {
		boolean flag = false;
		try {
			getSession().saveOrUpdate(entity);
			flag = true;
		} catch (Exception e) {
			flag = false;
			throw new HibernateException(e);
		}
		return flag;
	}

	public boolean merge(T entity) {
		boolean flag = false;
		try {
			getSession().merge(entity);
			flag = true;
		} catch (Exception e) {
			flag = false;
			throw new HibernateException(e);
		}
		return flag;
	}

	public boolean deleteByKey(PK id) {
		return this.delete(this.get(id));
	}

	// -------------------- HSQL ----------------------------------------------

	public boolean deleteByQuery(String hql) {
		boolean flag = false;
		try {
			getSession().createQuery(hql).executeUpdate();
			flag = true;
		} catch (Exception e) {
			flag = false;
			throw new HibernateException(e);
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	public List<T> findBySQL(String hql) {
		return getSession().createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	public T findUniqueObjectBySQL(String hql) {
		return (T) getSession().createQuery(hql).uniqueResult();
	}

	/**
	 * 通过vo类某一属性名及值，查出其具体某个类;
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public final T getUniqueByProperty(String propertyName, Object value) {
		return (T) createCriteria(Restrictions.eq(propertyName, value)).uniqueResult();
	}

	/**
	 * 创建Criteria对象.
	 * 
	 * @param criterions
	 *            可变的Restrictions条件列表
	 */
	protected final Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	/*
	 * 根据属性名与其值查询出具体的数据列表
	 */
	@SuppressWarnings("unchecked")
	public final List<T> listByProperty(String propertyName, Object value) {
		try {
			return createCriteria(value != null ? Restrictions.eq(propertyName, value) : Restrictions.isNull(propertyName)).list();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw re;
		}
	}

	/*
	 * 根据属性名与其值查询出具体的数据列表,并按参数排序;
	 */
	@SuppressWarnings("unchecked")
	public final List<T> listByPropertyOrdered(String propertyName, Object value, String orderBy, boolean isAsc) {
		return createCriteria(orderBy, isAsc, value != null ? Restrictions.eq(propertyName, value) : Restrictions.isNull(propertyName)).list();
	}

	/**
	 * 创建Criteria对象，带排序字段与升降序字段.
	 * 
	 */
	protected final Criteria createCriteria(String orderBy, boolean isAsc, Criterion... criterions) {

		Criteria criteria = createCriteria(criterions);
		if (isAsc) {
			criteria.addOrder(Order.asc(orderBy));
		} else {
			criteria.addOrder(Order.desc(orderBy));
		}
		return criteria;
	}

	/**
	 * 根据属性名与其值统计数量;
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public final long countByProperty(String propertyName, Object value) {
		return  (long) createCriteria(Restrictions.eq(propertyName, value)).setProjection(Projections.rowCount()).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<T> findObjectListsByPage(String hql, int firstRow, int maxRow) {
		return getSession().createQuery(hql).setFirstResult(firstRow).setMaxResults(maxRow).list();
	}

	/**
	 * 关闭声明对象
	 * 
	 * @param psmt
	 *            声明对象
	 */
	public void close(final Statement psmt) {
		try {
			if (psmt != null)
				psmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭返回对象
	 * 
	 * @param rs
	 *            返回结果集对象
	 */
	public void close(final ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeConn(final Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}
}
