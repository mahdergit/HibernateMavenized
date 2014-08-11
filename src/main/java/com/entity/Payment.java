package com.entity;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Payment {
@Id
@GeneratedValue
private int id;
@OneToOne
private Order order;
@ManyToOne
private CreaditCard creaditCard;

@Temporal(TemporalType.DATE)
private Date paymentDate;


}
