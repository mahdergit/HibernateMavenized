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
	private List<Product> allProducts;
	private String image;
<<<<<<< HEAD
	private String categoryType;
	private static SessionFactory sf = HibernateUtil.getSessionFactory();
=======
	private String prctDescription;
	public String getPrctDescription() {
		return prctDescription;
	}
>>>>>>> origin/Mahder

	public void setPrctDescription(String prctDescription) {
		this.prctDescription = prctDescription;
	}

	private static SessionFactory sf = HibernateUtil.getSessionFactory();
	public ProductControl(){
		prDao = new ProductDAO();
	}
	public Product getProduct() {
<<<<<<< HEAD
		if(product==null){
			product=new Product();
		}
		return product;
	}

	public List<Product> getProducts() {
		return products;
	}
	
	public void setProducts(List<Product> products) {
		this.products = products;
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
	public List<Product> getAllProducts() {
		tx = sf.getCurrentSession().beginTransaction();
		if(allProducts==null)
			allProducts=prDao.selectAllEntity();	
		tx.commit();
		return allProducts;
	}
	public String catagorizedProduct(String cat){
		tx = sf.getCurrentSession().beginTransaction();
		allProducts=null;
		allProducts=prDao.searchEntityCategory(cat);
		tx.commit();
		return null;
	}
	public void setAllProducts(List<Product> allProducts) {
		this.allProducts = allProducts;
	}
	public String searchProduct() {
		tx = sf.getCurrentSession().beginTransaction();
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
		return "null";
		}
	}

	private Transaction tx;

	public String moveToCart() {
=======
		
		return product;
	}
public String getProductdescription(){
	//prctDescription=product.getDescription();
	prctDescription="product description!!";
	return null;
}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public String searchProduct() {
		return null;
	}
	
	public String moveToCart(){
>>>>>>> origin/Mahder
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
