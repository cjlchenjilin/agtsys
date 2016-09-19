package org.agtsys.dao;

import org.agtsys.domain.User;

public interface UserMapper {
	User getUserByUser(User user);
	int updateUserByUser(User user);
}