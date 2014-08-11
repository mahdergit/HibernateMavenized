package com.control;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.dao.AbstractFacade;
import com.dao.AccountDAO;
import com.dao.HibernateUtil;
import com.dao.ShoppingCartDAO;
import com.entity.Product;
import com.entity.ShoppingCart;

@Named("shoppingControl")
@SessionScoped
public class ShoppingControl implements Serializable{
	
	private static SessionFactory sf = HibernateUtil.getSessionFactory();
	private AbstractFacade scDao;
	private ShoppingCart shoppingCart;
	
	public ShoppingControl() {
		scDao=new ShoppingCartDAO();
	}
	public ShoppingCart getSelected(){
		if(shoppingCart==null)
			shoppingCart=new ShoppingCart();
		return shoppingCart;
	}
	public void checkout(){
		
	}
	public void continueShopping(){
		
	}
	@SuppressWarnings("unchecked")
	public String removeProduct(Product product){
		Transaction tx = sf.getCurrentSession().beginTransaction();
		List<Product> products=shoppingCart.getProduct();
		products.remove(products);
		scDao.updateEntity(shoppingCart);
		tx.commit();
		return null;
	}
	@SuppressWarnings("unchecked")
	public String deleteShoppingCart(ShoppingCart shoppingCart){
		scDao.deleteEntity(shoppingCart);
		return null;
	}
	
	
	
}
