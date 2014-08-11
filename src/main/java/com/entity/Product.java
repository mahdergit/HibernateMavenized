package com.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Product {
@Id
private String image;
@ManyToMany(mappedBy="products")
private List<Order> orders;
@ManyToMany(mappedBy="product")
private List<ShoppingCart> shoppingCart;
@ManyToMany
private List<ProductCategory> productCategories;

private String name;
private double price;
private String description;
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public List<Order> getOrders() {
	return orders;
}
public void setOrders(List<Order> orders) {
	this.orders = orders;
}
public List<ShoppingCart> getShoppingCart() {
	return shoppingCart;
}
public void setShoppingCart(List<ShoppingCart> shoppingCart) {
	this.shoppingCart = shoppingCart;
}
public List<ProductCategory> getProductCategories() {
	return productCategories;
}
public void setProductCategories(List<ProductCategory> productCategories) {
	this.productCategories = productCategories;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}


}
