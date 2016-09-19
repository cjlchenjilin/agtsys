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
import org.springframework.web.bind.annotation.ResponseBody;

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
	//退出系统
	@RequestMapping("logout")
	public String logout(HttpSession session){
		session.removeAttribute(WebConstant.SESSION_USER_KEY);
		return "redirect:login";
	}
	//密码验证
	@ResponseBody
	@RequestMapping("pwd/check")
	public String checkpwd(String pwd,HttpSession session){
		if (pwd == null || pwd.trim().equals("")) {
			return "empty";
		}
		String result = "";
		User login_user = (User) session.getAttribute(WebConstant.SESSION_USER_KEY);
		login_user.setUserpassword(pwd);
		User user = userService.getUserByUser(login_user);
		if (user != null) {
			result="ok";
		} else {
			result="error";
		}
		return result;
	}
	//修改密码
	@ResponseBody
	@RequestMapping(value = "/pwd/update", method = RequestMethod.POST)
	public String updatePwd(String newpwd, String twopwd, HttpSession session) {
		if (newpwd == null || newpwd.trim().equals("")) {
			return "empty";
		}
		if (twopwd == null || twopwd.trim().equals("")) {
			return "two-empty";
		}
		if (!newpwd.equals(twopwd)) {
			return  "not-equals";
		}
		User login_user = (User) session.getAttribute(WebConstant.SESSION_USER_KEY);
		login_user.setUserpassword(newpwd);
		if (userService.updateUserByUser(login_user)==1){
			return "ok";
		} else {
			return "fail";
		}
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}