package org.agtsys.service;

import org.agtsys.domain.User;

public interface IUserService {
	User getUserByUser(User user);
	int updateUserByUser(User user);
}
