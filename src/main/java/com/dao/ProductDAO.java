package com.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.entity.Account;
import com.entity.Product;
import com.entity.ProductCategory;

public class ProductDAO extends AbstractFacade<Product> {

	public ProductDAO() {
		super(Product.class);
		// TODO Auto-generated constructor stub
	}

	private static SessionFactory sf = HibernateUtil.getSessionFactory();

	@Override
	public List<Product> searchEntityByName(String name) {
		List<Product> products = null;
		Query query = sf.getCurrentSession().createQuery(
				"SELECT distinct p FROM Product p WHERE p.name = :name");
		query.setParameter("name", name);
		products = query.list();
		System.out.println(products.get(0).getDescription());
		System.out.println(products.get(0).getPrice());
		return products;
	}
	public ProductCategory category(int id) {
		ProductCategory cat = new ProductCategory();
		Query query = sf.getCurrentSession().createQuery(
				"SELECT distinct p FROM ProductCategory p WHERE p.id = :id");
		query.setParameter("id", id);
		cat = (ProductCategory) query.list().get(0);
		System.out.println(cat.getCategoryDescription());
		return cat;
	}

/*	@Override
	public ProductCategory searchEntityCategory(String type) {
		ProductCategory cat=null;
		List<Product> products = null;
		Query query = sf.getCurrentSession().createQuery("SELECT distinct p FROM ProductCategory p WHERE p.categoryType = :id");
			//	"SELECT distinct p FROM Product p join p.productCategories c WHERE c.categoryType = :type");
		query.setParameter("id", type);
		cat = (ProductCategory) query.list().get(0);
		System.out.println(products.get(0).getDescription());
		System.out.println(products.get(0).getPrice());
		return cat;
	}*/
	public ProductCategory searchCategory(String type) {
		ProductCategory cat=null;
		List<Product> products = null;
		Query query = sf.getCurrentSession().createQuery("SELECT distinct p FROM ProductCategory p WHERE p.categoryType = :id");
			//	"SELECT distinct p FROM Product p join p.productCategories c WHERE c.categoryType = :type");
		query.setParameter("id", type);
		cat = (ProductCategory) query.list().get(0);
		System.out.println(cat.getCategoryDescription());
		//System.out.println(products.get(0).getPrice());
		return cat;
	}
	public static void main(String [] args){
		Transaction tx = sf.getCurrentSession().beginTransaction();
		ProductDAO d=new ProductDAO();
		Product p=(Product) d.searchEntityByName("testName").get(0);
		ProductCategory cat=(ProductCategory) d.searchCategory("birthday");
		cat.getProducts().add(p);
		p.getProductCategories().add(cat);
		AbstractFacade dd=new ProductDAO();
		
		//dd.updateEntity(cat);
		//dd.updateEntity(p);
		tx.commit();
	}

}
