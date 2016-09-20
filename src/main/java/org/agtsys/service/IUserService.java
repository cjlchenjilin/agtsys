package org.agtsys.service;

import java.util.List;
import org.agtsys.domain.User;
import org.agtsys.util.PageTool;


public interface IUserService {
	User getUserByUser(User user);
	int updateUserByUser(User user);
	List<User> getUserList(User user, PageTool pt);
	Integer getCount(User user);
//	int deleteUser(User user);
	int tx_deleteUser(User user);
	int tx_addUser(User user);
}
