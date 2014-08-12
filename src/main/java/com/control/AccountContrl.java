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
	private boolean edited;
	
	private boolean loggedIn=false;

	public boolean isEdited() {
		return edited;
	}


	public void setEdited(boolean edited) {
		this.edited = edited;
	}


	public boolean isLoggedIn() {
		return loggedIn;
	}


	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}


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
		Transaction tx = sf.getCurrentSession().beginTransaction();
		AccountDAO dao=new AccountDAO();
		String pass=account.getPassword();
		account=dao.getAccountByEmail(account.getEmail());
		if(account==null){//we can do rule for the error later
			return "login.xhtml";			
		}
		
		if(pass.equals(account.getPassword()))
		{
			loggedIn = true;
			return "profile.xhtml";
		}
			
		
			
		tx.commit();
		return "login.xhtml";
		
			
		
		
	}
	
	@SuppressWarnings("unchecked")
	public String editProfile(){

		Transaction tx = sf.getCurrentSession().beginTransaction();
		acDao.updateEntity(account);
		edited=false;
		tx.commit();
		return null;
	}
	public String readyEditProfile(){
		edited=true;
		return null;
	}

}
