package com.entity;


import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="OurOrder")
public class Order {
@Id
@GeneratedValue
private int id;
@ManyToOne
private Account account;
@OneToOne(mappedBy="order")
private Payment payment;
@ManyToMany
private List<Product> products;

@Temporal(TemporalType.DATE)
private Date orderDate;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public Account getAccount() {
	return account;
}

public void setAccount(Account account) {
	this.account = account;
}

public Payment getPayment() {
	return payment;
}

public void setPayment(Payment payment) {
	this.payment = payment;
}

public List<Product> getProducts() {
	return products;
}

public void setProducts(List<Product> products) {
	this.products = products;
}

public Date getOrderDate() {
	return orderDate;
}

public void setOrderDate(Date orderDate) {
	this.orderDate = orderDate;
}






}
