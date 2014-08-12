package com.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.persistence.internal.oxm.schema.model.List;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.dao.AbstractFacade;
import com.dao.CartDAO;
import com.dao.HibernateUtil;
import com.entity.Account;
import com.entity.Product;
import com.entity.ShoppingCart;

@Named("cartControl")
@SessionScoped
public class CartControl implements Serializable {
	private ShoppingCart shoppingCart;
	private int productQuanity;
	private CartDAO cartDAO;
	private int totalNoOfProducts;
	
	
//	private int prodQuantiy;
	
	
//
//	public void setProdQuantiy(int prodQuantiy) {
//		this.prodQuantiy = prodQuantiy;
//	}

	private static SessionFactory sf = HibernateUtil.getSessionFactory();
	@Inject
	private AccountContrl accountContrl;
	@Inject
	private ProductControl productControl;

	private double totalPriceOfAProduct;
	public double getTotalPriceOfAProduct() {
		return totalPriceOfAProduct;
	}
	public void setTotalPriceOfAProduct(double totalPriceOfAProduct) {
		this.totalPriceOfAProduct = totalPriceOfAProduct;
	}
	public CartControl() {
		cartDAO = new CartDAO();
	}
	public int getProdQuantiy(String productid,double prodPrice) {
		Transaction tx = sf.getCurrentSession().beginTransaction();
		int prodQuantiy=cartDAO.getProductQuantiy(productid);
		tx.commit();
		totalPriceOfAProduct = prodQuantiy * prodPrice;
		return prodQuantiy;
	}
	public int getTotalNoOfProducts() {
		totalNoOfProducts = shoppingCart.getProduct().size();
		return totalNoOfProducts;
	}

	public void setTotalNoOfProducts(int totalNoOfProducts) {
		this.totalNoOfProducts = totalNoOfProducts;
	}

	public int getProductQuanity() {
		return productQuanity;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public void setProductQuanity(int productQuanity) {
		this.productQuanity = productQuanity;
	}

	public String addToCart() {
		String retStrg = null;
		double price = 0;
		Transaction tx = sf.getCurrentSession().beginTransaction();
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			shoppingCart.setStartDate(new Date());
			cartDAO.saveEntity(shoppingCart);
		}

		for (int i = 0; i < productQuanity; i++) {
			if (shoppingCart.getProduct() == null) {
				ArrayList<Product> list = new ArrayList<>();
				shoppingCart.setProduct(list);
			}
			shoppingCart.getProduct().add(productControl.getProduct());
			price = price + productControl.getProduct().getPrice();
		}
		shoppingCart.setTotalPrice(shoppingCart.getTotalPrice() + price);
		cartDAO.updateEntity(shoppingCart);
		tx.commit();
		return "continueOrCheckout";

	}

	public void getCart(Account account) {

	}

	public void removeFromCart(Product product) {

	}

	public void removeCart() {

	}

}
