package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.entity.Account;
import com.entity.ShoppingCart;

public class CartDAO extends AbstractFacade<ShoppingCart> {

	public CartDAO() {
		super(ShoppingCart.class);
		// TODO Auto-generated constructor stub
	}

	private SessionFactory sf = HibernateUtil.getSessionFactory();

	@SuppressWarnings("unchecked")
	public int getProductQuantiy(String productid) {
		Query query = sf.getCurrentSession().createQuery(
				"select s from ShoppingCart s "
						+ "Join s.product p where p.id = :id");
		query.setParameter("id", productid);
		List<ShoppingCart> list = query.list();
		return list.size();
	}
	

}