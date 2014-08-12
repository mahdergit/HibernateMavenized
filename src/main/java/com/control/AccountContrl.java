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

	private String requestedUrl;
	
	private boolean loggedIn=false;
	public AccountContrl() {
		acDao = new AccountDAO();
	}

	public String getRequestedUrl() {
		return requestedUrl;
	}

	public void setRequestedUrl(String requestedUrl) {
		this.requestedUrl = requestedUrl;

	}

	public boolean isLoggedIn() {
		return loggedIn;
	}


	public boolean isEdited() {
		return edited;
	}


	public void setEdited(boolean edited) {
		this.edited = edited;
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

	
	@SuppressWarnings("unchecked")
	public String createAccount() {
		Transaction tx = sf.getCurrentSession().beginTransaction();

		System.out.println("create account" + account.getFirstName());
		acDao.saveEntity(account);

		tx.commit();
		loggedIn = true;
		return "selectedProducts";
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
	}
	
	@SuppressWarnings("finally")
	public String doLogin(){
		Transaction tx = sf.getCurrentSession().beginTransaction();
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
