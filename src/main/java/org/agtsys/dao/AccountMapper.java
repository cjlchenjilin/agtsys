package org.agtsys.dao;

import java.util.List;
import org.agtsys.domain.Account;

public interface AccountMapper {
	List<Account> getAccountList(Account account);
	Account getAccount(Account account);
    int addAccount(Account account);
    int updateAccount(Account account);
    int deleteAccount(Account account);
}