package com.base.dao;

import java.io.Serializable;
import org.hibernate.Cache;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SessionFactoryBase{

  @Autowired
  private SessionFactory sessionFactory;
  private Session session;

  public SessionFactory getSessionFactory()
  {
    return this.sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public Session getSession() {
    try{
      this.session = this.sessionFactory.getCurrentSession();
    } catch (HibernateException e) {
      if (this.session == null || !this.session.isOpen()) {
        this.session = this.sessionFactory.openSession();
      }
    }
    return this.session;
  }

  public void clearSessionFactoryCache(Class<? extends Serializable> c){
    getSessionFactory().getCache().evictEntityRegion(c);
  }
}