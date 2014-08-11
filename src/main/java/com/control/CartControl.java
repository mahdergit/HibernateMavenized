package com.control;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.dao.AbstractFacade;
import com.dao.CartDAO;
import com.entity.Account;
import com.entity.Product;
import com.entity.ShoppingCart;

@Named("cartControl")
@SessionScoped
public class CartControl implements Serializable {
	private ShoppingCart shoppingCart;
	private int productQuanity;
	private AbstractFacade cartDAO;
	private int totalNoOfProducts;
	@Inject
	private ProductControl productControl;
   
	public CartControl() {
		cartDAO = new CartDAO();
	}

	public int getTotalNoOfProducts() {
		totalNoOfProducts=shoppingCart.getProduct().size();
		return totalNoOfProducts;
	}

	public void setTotalNoOfProducts(int totalNoOfProducts) {
		this.totalNoOfProducts = totalNoOfProducts;
	}

	public int getProductQuanity() {
		return productQuanity;
	}

	public void setProductQuanity(int productQuanity) {
		this.productQuanity = productQuanity;
	}

	public String addToCart() {
		double price=0;
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
		}
		for (int i = 0; i < productQuanity; i++) {
			shoppingCart.getProduct().add(productControl.getProduct());
			price=price+ productControl.getProduct().getPrice();			
		}
		shoppingCart.setTotalPrice(shoppingCart.getTotalPrice()+price);
		return "continueOrCheckout";

	}

	public void getCart(Account account) {

	}

	public void removeFromCart(Product product) {

	}

	public void removeCart() {

	}

}
