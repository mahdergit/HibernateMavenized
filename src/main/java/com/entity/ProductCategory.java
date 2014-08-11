package com.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class ProductCategory {
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToMany(mappedBy="productCategories")
	private List<Product> products;
	
	private String categoryDescription;
	private String categoryType;
	
	

}
