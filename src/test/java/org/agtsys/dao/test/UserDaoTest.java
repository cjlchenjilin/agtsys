package org.agtsys.dao.test;

import org.agtsys.dao.UserMapper;
import org.agtsys.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserDaoTest {
	private UserMapper userMapper;
	@Before
	public void setUp() throws Exception {
		ApplicationContext ac =new ClassPathXmlApplicationContext("spring-mybatis.xml");
		userMapper=(UserMapper)ac.getBean("userMapper");
	}

	@Test
	public void testGetUserbyUser() {
		User user = new User();
		user.setUsercode("admin");
		user.setUserpassword("123456");
		Assert.assertNotNull(userMapper.getUserByUser(user));
	}

}
