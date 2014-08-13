package com.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.entity.Product;


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
	
	public List<T> searchEntityByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> searchEntityCategory(String type) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<T> selectAllEntity() {
		// TODO Auto-generated method stub
		return null;
	}




}
