package com.dao;


import java.util.Collection;

import javax.transaction.Transaction;

import org.hibernate.SessionFactory;

import com.entity.ShoppingCart;

public class ShoppingCartDAO extends AbstractFacade<ShoppingCart>{

	public ShoppingCartDAO(){
		super(ShoppingCart.class);
	}
	private SessionFactory sf = HibernateUtil.getSessionFactory();
	

	@SuppressWarnings("unchecked")
	public Collection<ShoppingCart> getShoppingCart() {
		return sf.getCurrentSession().createQuery("select * from ShoppingCart").list();
	}

	public ShoppingCart loadShoppingCart(int shoppingcartnumber) {
		return (ShoppingCart) sf.getCurrentSession().get(ShoppingCart.class, shoppingcartnumber);
	}

	public void saveShoppingCart(ShoppingCart cart) {

		sf.getCurrentSession().save(cart);
	}

	public void updateShoppingCart(ShoppingCart cart) {
		sf.getCurrentSession().saveOrUpdate(cart);
	}
	public void deleteShoppingCart(ShoppingCart cart) {
		sf.getCurrentSession().delete(cart);
	}


}

