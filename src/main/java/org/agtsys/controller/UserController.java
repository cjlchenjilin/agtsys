package org.agtsys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.agtsys.constant.WebConstant;
import org.agtsys.domain.User;
import org.agtsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("user/")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String dologin(String captchca,User user,HttpServletRequest request){
		//返回页面
		String ret_page = "login";
		//判断验证码是否正确
		if (captchca == null || captchca.trim().equals("")) {
			request.setAttribute("msg", "验证码为空！！");
			return ret_page;
		} else {
			HttpSession session = request.getSession();
			if (!captchca.equals(session
					.getAttribute(WebConstant.SESSION_CAPTCHCA_KEY))) {
				request.setAttribute("msg", "验证码错误！！");
				return ret_page;
			}
		}
		//判断用户
		if (user == null) {
			request.setAttribute("msg", "用户名或者密码为空！！");
		} else {
			User ret_user = userService.getUserByUser(user);
			//返回空对象，说明数据库没有这个记录
			if (ret_user == null) {
				request.setAttribute("msg", "用户名或者密码错误！！");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute(WebConstant.SESSION_USER_KEY, ret_user);
				ret_page = "main";
			}
		}
		return ret_page;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}