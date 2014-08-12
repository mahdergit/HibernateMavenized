package com.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.entity.Product;

public class ProductDAO extends AbstractFacade<Product>{

	public ProductDAO() {
		super(Product.class);
		// TODO Auto-generated constructor stub
	}

	//private SessionFactory sf = HibernateUtil.getSessionFactory();

}



