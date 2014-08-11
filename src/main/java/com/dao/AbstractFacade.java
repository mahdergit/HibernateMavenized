package com.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;


public abstract class AbstractFacade<T> {
	private SessionFactory sf = HibernateUtil.getSessionFactory();
	
	private Class<T> entityClass;
	
	public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
	
	public T loadEntity(Object id) {
		return (T) sf.getCurrentSession().get(entityClass, (Serializable) id);
	}

	
	public void saveEntity(T entity) {
		sf.getCurrentSession().save(entity);
	}

	public void updateEntity(T entity) {
		sf.getCurrentSession().saveOrUpdate(entity);
	}
	public void deleteEntity(T entity) {
		sf.getCurrentSession().delete(entity);
	}

}
