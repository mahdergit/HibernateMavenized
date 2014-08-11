package com.control;

import java.io.Serializable;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.dao.AbstractFacade;
import com.dao.ProductDAO;
import com.entity.Product;

@Named("productControl")
@SessionScoped
public class ProductControl implements Serializable{
	private AbstractFacade prDao;
	private Product product;
	private String image;
	
	public ProductControl(){
		prDao = new ProductDAO();
	}
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public String searchProduct() {
		return null;
	}
	
	public String moveToCart(){
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();
		image = params.get("url");
		product = (Product) prDao.loadEntity(image);
		return "addToCart";
		
		
	}

}
