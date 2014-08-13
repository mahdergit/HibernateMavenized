package com.control;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.dao.AbstractFacade;
import com.dao.AccountDAO;
import com.dao.CartDAO;
import com.dao.HibernateUtil;
import com.entity.Product;
import com.entity.ShoppingCart;
import com.dao.OrderDAO;
import com.entity.Order;

@Named("shoppingControl")
@SessionScoped
public class ShoppingControl implements Serializable {
	private String customerType;
	private AbstractFacade orderDAO;
	private AbstractFacade scDao;
	private static SessionFactory sf = HibernateUtil.getSessionFactory();
	@Inject
	private AccountContrl accountContrl;

	public ShoppingControl() {
		orderDAO = new OrderDAO();
		scDao=new CartDAO();
	}

	public String getCustomerType() {
		return customerType;
	}
	/*@SuppressWarnings("unchecked")
	public String removeProduct(Product product){
		Transaction tx = sf.getCurrentSession().beginTransaction();
		List<Product> products=scDao.getProduct
		products.remove(products);
		scDao.updateEntity(CartDAO);
		tx.commit();
		return null;
	}*/
	@SuppressWarnings("unchecked")
	public String deleteShoppingCart(ShoppingCart shoppingCart){
		scDao.deleteEntity(shoppingCart);
		return null;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String checkout() {
		String strig = null;
		if (!accountContrl.isLoggedIn()) {//There is a checkLogin method in account
			strig = "login";
		} else {
			Transaction tx = sf.getCurrentSession().beginTransaction();
			Order order = new Order();
			order.setOrderDate(new Date());
			order.setAccount(accountContrl.getAccount());
			orderDAO.saveEntity(order);
			tx.commit();
			strig = "checkoutsuccess";
		}

		return strig;

	}

	public void continueShopping() {

	}
}
