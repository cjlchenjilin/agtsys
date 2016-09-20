package org.agtsys.dao;

import java.util.List;

import org.agtsys.domain.User;
import org.agtsys.util.PageTool;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
	User getUserByUser(User user);
	int updateUserByUser(User user);
	List<User> getUserList(@Param("user") User user,@Param("pt") PageTool pt);
	Integer getCount(User user);
	Integer addUser(User user);
	Integer deleteUser(User user);
}