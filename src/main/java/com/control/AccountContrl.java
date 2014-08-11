package com.control;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.dao.AbstractFacade;
import com.dao.AccountDAO;
import com.dao.HibernateUtil;
import com.dao.IAccountDAO;
import com.entity.Account;
import com.entity.Product;

@Named("accountCont")
@SessionScoped
public class AccountContrl implements Serializable {
	AbstractFacade acDao;
	private Account account;
	

	public Account getAccount() {
		return account;
	}

	

	public Account getSelected() {
		if (account == null) {
			account = new Account();
		}
		System.out.println("get selected" + account.getFirstName());
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	private static SessionFactory sf = HibernateUtil.getSessionFactory();
	

	public AccountContrl() {
		acDao = new AccountDAO();
	}

	public String createAccount() {
		Transaction tx = sf.getCurrentSession().beginTransaction();

		System.out.println("create account" + account.getFirstName());
		acDao.saveEntity(account);

		tx.commit();

		return null;
	}
	public void logout(){
		
	}

	public String doLogin() {
		return null;
	}
	
	public void editProfile(){
		
	}
	

	
	
	
}
