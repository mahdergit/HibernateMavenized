package com.control;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.dao.AbstractFacade;
import com.dao.HibernateUtil;
import com.dao.OrderDAO;
import com.entity.Order;

@Named("shoppingControl")
@SessionScoped
public class ShoppingControl implements Serializable {
	private String customerType;
	private AbstractFacade orderDAO;
	private static SessionFactory sf = HibernateUtil.getSessionFactory();
	@Inject
	private AccountContrl accountContrl;
	@Inject
	private CartControl cartControl;

	public ShoppingControl() {
		orderDAO = new OrderDAO();
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String checkout() {
		String strig = null;
		if (!accountContrl.isLoggedIn()) {
			strig = "login";
		} else {
			Transaction tx = sf.getCurrentSession().beginTransaction();
			Order order = new Order();
			order.setOrderDate(new Date());
			order.setAccount(accountContrl.getAccount());
			order.setProducts(cartControl.getShoppingCart().getProduct());
			orderDAO.saveEntity(order);
			tx.commit();
			strig = "checkoutsuccess";
		}

		return strig;

	}

	public void continueShopping() {

	}

}
