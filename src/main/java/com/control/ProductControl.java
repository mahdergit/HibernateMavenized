package com.control;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import rules.MessageProvider;
import rules.MessagesUtil;
import rules.RulesException;

import com.dao.AbstractFacade;
import com.dao.AccountDAO;
import com.dao.HibernateUtil;
import com.dao.ProductDAO;
import com.entity.Product;

@Named("productControl")
@SessionScoped
public class ProductControl implements Serializable{
	private AbstractFacade prDao;
	private Product product;
	private List<Product> products;
	private String image;
	private String categoryType;
	private static SessionFactory sf = HibernateUtil.getSessionFactory();
	public ProductControl(){
		prDao = new ProductDAO();
	}
	public Product getProduct() {
		if(product==null){
			product=new Product();
		}
		return product;
	}

	public String getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	@SuppressWarnings({ "unchecked", "finally" })
	public String searchProduct() {
		Transaction tx = sf.getCurrentSession().beginTransaction();
		MessageProvider mp=new MessageProvider();
		String name=product.getName();
		try{
			if(name==null||categoryType==null)
				throw new RulesException(mp.getValue("noCatnoName"));
			else if(categoryType==null)
				products=prDao.searchEntityByName(name);
			else 
				products=prDao.searchEntityCategory(categoryType);
			if(products.isEmpty())
				 throw new RulesException(mp.getValue("prodNotFound"));
		}
		catch(RulesException e) {
			MessagesUtil.displayError(e.getMessage());
		} finally {
			tx.commit();
		return null;
		}
	}
	
	public String moveToCart(){
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();
		image = params.get("image");
		Transaction tx = sf.getCurrentSession().beginTransaction();
		product = (Product) prDao.loadEntity(image);
		tx.commit();
		
		return "addToCart";
		
		
	}

}
