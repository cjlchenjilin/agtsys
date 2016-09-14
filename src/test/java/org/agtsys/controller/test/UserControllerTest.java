package org.agtsys.controller.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class UserControllerTest extends SpringMvcBaseTest {
	private MockHttpServletRequest request;
	
	@Before
	public void setUp(){
		super.setUp();
		request = new MockHttpServletRequest();
	}
	@Test
	public void testGetLogin() {
		
		try {
			mvc.perform((MockMvcRequestBuilders.get("/user/login"))).
			andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
