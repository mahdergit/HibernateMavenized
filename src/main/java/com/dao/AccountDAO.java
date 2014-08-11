package com.dao;


import java.util.Collection;

import javax.transaction.Transaction;

import org.hibernate.SessionFactory;

import com.entity.Account;
import com.sun.xml.ws.runtime.dev.Session;

public class AccountDAO extends AbstractFacade<Account>{

	public AccountDAO() {
		super(Account.class);
		// TODO Auto-generated constructor stub
	}


	private SessionFactory sf = HibernateUtil.getSessionFactory();
	

	@SuppressWarnings("unchecked")
	public Collection<Account> getAccounts() {
		// To prevent our Lazy Initialization problems 
		// we don't have open session in view, so instead we have to eagerly load!
//		return sf.getCurrentSession().createQuery("select distinct a from Account a " +
//				"join fetch a.customer " +
//				"join fetch a.entryList").list();
		return sf.getCurrentSession().createQuery("select * from Account").list();
	}


}

