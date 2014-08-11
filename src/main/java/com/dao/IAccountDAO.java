package com.dao;

import java.util.Collection;

import com.entity.Account;

public interface IAccountDAO {
	public void saveAccount(Account account);
	public void updateAccount(Account account);
	public Account loadAccount(int id);
	public Collection<Account> getAccounts();
}
