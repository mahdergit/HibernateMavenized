package com.control;

import java.io.Serializable;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.dao.AbstractFacade;
import com.dao.HibernateUtil;
import com.dao.ProductDAO;
import com.entity.Product;

@Named("productControl")
@SessionScoped
public class ProductControl implements Serializable {
	private AbstractFacade prDao;
	private Product product;
	private String image;
	private String prctDescription;
	public String getPrctDescription() {
		return prctDescription;
	}

	public void setPrctDescription(String prctDescription) {
		this.prctDescription = prctDescription;
	}

	private static SessionFactory sf = HibernateUtil.getSessionFactory();

	public ProductControl() {
		prDao = new ProductDAO();
	}

	public Product getProduct() {
		
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

	private Transaction tx;

	public String moveToCart() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();
		image = params.get("image");
		try {
			tx = sf.getCurrentSession().beginTransaction();
			product = (Product) prDao.loadEntity(image);

			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		}

		return "productToCart";

	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
