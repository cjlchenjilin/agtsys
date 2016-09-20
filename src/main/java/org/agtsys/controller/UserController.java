package org.agtsys.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.agtsys.constant.WebConstant;
import org.agtsys.domain.Role;
import org.agtsys.domain.User;
import org.agtsys.service.IRoleService;
import org.agtsys.service.IUserService;
import org.agtsys.service.UserService;
import org.agtsys.util.MD5;
import org.agtsys.util.PageTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user/")
public class UserController {
	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;
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
	@RequestMapping("manage")
	public String userManage(Model model){
		List<Role> roles=roleService.getAllRoles();
		model.addAttribute("roles",roles);
		return "userlist";
	}
	@ResponseBody
	@RequestMapping(value="list")
	public Object get_user_list(Integer page,Integer rows,User user){
		Map<String,Object> map = new HashMap<String,Object>();
		List<User> users = new ArrayList<User>();
		users = userService.getUserList(user, new PageTool(page,rows,null));
		Integer total = userService.getCount(user);
		map.put("total", total);
		map.put("rows", users);
		return map;
	}
	@ResponseBody
	@RequestMapping(value = "check", method = RequestMethod.GET)
	public Object checkUser(User user) {
		if (user.getUsercode() == null || user.getUsercode().trim().equals("")) {
			return "empty";
		}
		User ret_user = userService.getUserByUser(user);
		if (ret_user != null) {
			return "exist";
		} else {
			return "no_exist";
		}
	}
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String addUser(Model model){
		List<Role> roles=roleService.getAllRoles();
		model.addAttribute("roles", roles);
		return "user_add";
	}
	@ResponseBody
	@RequestMapping(value="add",method=RequestMethod.POST)
	public Object addUserSubmit(User user,HttpSession session){
		user.setCreationtime(new Date());
		User login_user = (User) session.getAttribute(WebConstant.SESSION_USER_KEY);
		user.setCreatedby(login_user.getUsercode());
		if(userService.tx_addUser(user)==1){
			return "{\"msg\":1}";
		}
		return "{\"msg\":0}";
	}
	//加载被修改的用户信息
	@RequestMapping(value = "load/{id}", method = RequestMethod.GET)
	public String loadUser(@PathVariable("id") Long id, User user,Model model) {
		user.setId(id);
		user = userService.getUserByUser(user);
		model.addAttribute("user", user);
		List<Role> roles=roleService.getAllRoles();
		model.addAttribute("roles", roles);
		return "user_edit";
	}
	@ResponseBody
	@RequestMapping(value="update/{id}")
	public Object updateuser(@PathVariable("id") Long id,User user){
		user.setLastupdatetime(new Date());
		if(userService.updateUserByUser(user)==1){
			return "{\"msg\":1}";
		}
		return "{\"msg\":0}";
	}
	@ResponseBody
	@RequestMapping(value="delete/{id}")
	public Object deleteUser(@PathVariable("id") Long id,User user){
		//System.out.println(user.getId());
		if(userService.tx_deleteUser(user)==1){
			return "{\"msg\":1}";
		}
		return "{\"msg\":0}";
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
}