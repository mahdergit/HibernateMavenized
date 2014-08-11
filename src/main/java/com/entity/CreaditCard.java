package com.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CreaditCard {
@Id
@GeneratedValue
private int id;
@ManyToOne
private Account account;
@OneToMany(mappedBy="creaditCard")
private List<Payment> payments;


private Long cardNo;
@Temporal(TemporalType.DATE)
private Date expireDate;



}
