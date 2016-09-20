package org.agtsys.service;

import java.util.List;

import org.agtsys.dao.AccountMapper;
import org.agtsys.dao.UserMapper;
import org.agtsys.domain.Account;
import org.agtsys.domain.User;
import org.agtsys.util.PageTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements IUserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private AccountMapper accountMapper;


	@Override
	public User getUserByUser(User user) {
		return userMapper.getUserByUser(user);
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public int updateUserByUser(User user) {
		return userMapper.updateUserByUser(user);
	}

	@Override
	public List<User> getUserList(User user, PageTool pt) {
		// TODO Auto-generated method stub
		return userMapper.getUserList(user, pt);
	}

	@Override
	public Integer getCount(User user) {
		// TODO Auto-generated method stub
		return userMapper.getCount(user);
	}

	// 添加用户时，同时添加对应的账户,具有事务性质：原子性
	@Override
	public int tx_addUser(User user) {
		//返回id
		userMapper.addUser(user);
		//int a = 10/0;
		Account account = new Account();
		account.setUserid(user.getId());
		account.setMoney(0.0);
		account.setMoneybak(0.0);
		return accountMapper.addAccount(account);
	}

	public void setAccountMapper(AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}

	
	public int tx_deleteUser(User user) {
		//先删除账号
		Account account = new Account();
		account.setUserid(user.getId());
		accountMapper.deleteAccount(account);
		return userMapper.deleteUser(user);
	}
}
