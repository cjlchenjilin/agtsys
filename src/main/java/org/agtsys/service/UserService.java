package org.agtsys.service;

import org.agtsys.dao.UserMapper;
import org.agtsys.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
	@Autowired
	private UserMapper userMapper;
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
}
