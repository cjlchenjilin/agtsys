package org.agtsys.service;

import java.util.List;

import org.agtsys.domain.Account;
//import org.agtsys.domain.Accountdetail;

public interface IAccountService {
	List<Account> getAccountList(Account account);
	Account getAccount(Account account);
	int addAccount(Account account);
	int updateAccount(Account account);
	int deleteAccount(Account account);
	
	//logic transaction
	//boolean tx_operationAccount(Account oldAccount,Accountdetail accountdetail);
}
