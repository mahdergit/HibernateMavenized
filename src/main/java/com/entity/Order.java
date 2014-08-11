package com.entity;


import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Order {
@Id
@GeneratedValue
private int id;
@Temporal(TemporalType.DATE)
private Date date;
@ManyToOne
private Account account;
@OneToOne(mappedBy="order")
private Payment payment;
@ManyToMany
private List<Product> products;

@Temporal(TemporalType.DATE)
private Date orderDate;






}
