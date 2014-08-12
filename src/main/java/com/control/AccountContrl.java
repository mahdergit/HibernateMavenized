package com.control;

import java.io.Serializable;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import rules.RulesException;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import rules.MessageProvider;
import rules.MessagesUtil;

import com.dao.AbstractFacade;
import com.dao.AccountDAO;
import com.dao.HibernateUtil;
import com.entity.Account;

@Named("accountCont")
@SessionScoped
public class AccountContrl implements Serializable {
	private AbstractFacade acDao;
	private Account account;
	private boolean edited;

<<<<<<< HEAD
	private String requestedUrl;
	
	private boolean loggedIn=false;
	public AccountContrl() {
		acDao = new AccountDAO();
	}
=======
	private boolean loggedIn = false;
>>>>>>> origin/Mahder

	public String getRequestedUrl() {
		return requestedUrl;
	}

<<<<<<< HEAD
	public void setRequestedUrl(String requestedUrl) {
		this.requestedUrl = requestedUrl;

=======
	public void setEdited(boolean edited) {
		this.edited = edited;
>>>>>>> origin/Mahder
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

<<<<<<< HEAD

	public boolean isEdited() {
		return edited;
	}


	public void setEdited(boolean edited) {
		this.edited = edited;
	}
=======
>>>>>>> origin/Mahder
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
<<<<<<< HEAD
=======

	public AccountContrl() {
		acDao = new AccountDAO();
	}
>>>>>>> origin/Mahder

	
	@SuppressWarnings("unchecked")
	public String createAccount() {
		Transaction tx = sf.getCurrentSession().beginTransaction();

		System.out.println("create account" + account.getFirstName());
		acDao.saveEntity(account);

		tx.commit();
		loggedIn = true;
		return "selectedProducts";
<<<<<<< HEAD
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		loggedIn = false;
		return "index";
	}
	public String checkLogin() {
		FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params =
                fc.getExternalContext().getRequestParameterMap();
        requestedUrl = params.get("url");
		if(loggedIn) {
			return requestedUrl;
		} else {
			return "login";
	    }
=======
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		loggedIn = false;
		return "index";
>>>>>>> origin/Mahder
	}
	
	@SuppressWarnings("finally")
	public String doLogin(){
		Transaction tx = sf.getCurrentSession().beginTransaction();
<<<<<<< HEAD
		FacesContext fc = FacesContext.getCurrentInstance();
	    Map<String, String> params =
	            fc.getExternalContext().getRequestParameterMap();
	    MessageProvider mp=new MessageProvider();
	    /*if(requestedUrl.isEmpty()) {
	    	//requestedUrl = (params.get("url"));
	    }*/
		AccountDAO dao=new AccountDAO();
		String pass=account.getPassword();
		account=dao.getAccountByEmail(account.getEmail());
		try{
		if(account==null){
	    		throw new RulesException(mp.getValue("notFound"));
		}
		if(pass.equals(account.getPassword())){
			loggedIn=true;
		}
		if(!pass.equals(account.getPassword())){
			throw new RulesException(mp.getValue("errorNotMatch"));
		}
		}catch(RulesException e) {
			MessagesUtil.displayError(e.getMessage());
		} finally {
			tx.commit();
		return requestedUrl;
		}
=======
		AccountDAO dao = new AccountDAO();
		String pass = account.getPassword();
		account = dao.getAccountByEmail(account.getEmail());
		if (account == null) {// we can do rule for the error later
			return "login.xhtml";
		}

		if (pass.equals(account.getPassword())) {
			loggedIn = true;
			return "selectedProducts";
		}

		tx.commit();
		
		
		return "login.xhtml";

>>>>>>> origin/Mahder
	}

	@SuppressWarnings("unchecked")
	public String editProfile() {

		Transaction tx = sf.getCurrentSession().beginTransaction();
		acDao.updateEntity(account);
		edited = false;
		tx.commit();
		return null;
	}

	public String readyEditProfile() {
		edited = true;
		return null;
	}

}
