package org.agtsys.service;

import java.util.List;

import org.agtsys.dao.AccountMapper;
//import org.agtsys.dao.AccountdetailMapper;
import org.agtsys.domain.Account;
//import org.agtsys.domain.Accountdetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
	@Autowired
	private AccountMapper accountMapper;
	/*@Autowired
	private AccountdetailMapper accountdetailMapper;*/
	@Override
	public int addAccount(Account account) {
		return accountMapper.addAccount(account);
	}
	@Override
	public int deleteAccount(Account account) {
		return accountMapper.deleteAccount(account);
	}
	/*public void setAccountdetailMapper(AccountdetailMapper accountdetailMapper) {
		this.accountdetailMapper = accountdetailMapper;
	}*/
	public AccountMapper getAccountMapper() {
		return accountMapper;
	}
	public void setAccountMapper(AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}
	@Override
	public List<Account> getAccountList(Account account) {
		return accountMapper.getAccountList(account);
	}
	@Override
	public Account getAccount(Account account) {
		return accountMapper.getAccount(account);
	}
	@Override
	public int updateAccount(Account account) {
		return accountMapper.updateAccount(account);
	}
	/*@Override
	public boolean tx_operationAccount(Account oldAccount,
			Accountdetail accountdetail) {
		// 资金计算
		oldAccount.setMoney(oldAccount.getMoney()+accountdetail.getMoney());
		oldAccount.setMoneybak(oldAccount.getMoneybak()+accountdetail.getMoney());
		accountdetail.setAccountmoney(oldAccount.getMoney());
		//修改账户资金
		accountMapper.updateAccount(oldAccount);
		//记录流水
		accountdetailMapper.addAccountdetail(accountdetail);
		return true;
	}*/
}
