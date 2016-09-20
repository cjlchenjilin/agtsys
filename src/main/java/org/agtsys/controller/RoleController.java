package org.agtsys.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.agtsys.constant.WebConstant;
import org.agtsys.util.PageTool;
import org.agtsys.domain.Role;
import org.agtsys.domain.User;
import org.agtsys.service.IRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("role/")
public class RoleController {
	@Resource(name="roleService")
	private IRoleService roleService;
	
	@RequestMapping("manage")
	public String roleManage(){
		return "rolelist";
	}
	
	@ResponseBody
	@RequestMapping(value="list")
	public Object get_role_list(Integer page,Integer rows){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Role> roles = new ArrayList<Role>();
		roles = roleService.getPagedRoles(new PageTool(page,rows,null));
		Integer total = roleService.getTotalCount();
		map.put("total", total);
		map.put("rows", roles);
		return map;
	}
	@ResponseBody
	@RequestMapping(value = "check", method = RequestMethod.GET)
	public Object checkrole(Role role) {
		Map<String, Object> data = new HashMap<String, Object>();
		if (role.getRolename() == null || role.getRolename().trim().equals("")) {
			data.put("msg", "empty");
			return data;
		}
		Role ret_role = roleService.getRoleByRole(role);
		if (ret_role != null) {
			data.put("msg", "exist");
			return data;
		} else {
			data.put("msg", "no_exist");
			return data;
		}
	}
	@ResponseBody
	@RequestMapping(value="add")
	public Object addRole(Role role,HttpSession session){
		role.setCreationtime(new Date());
		User user = (User) session.getAttribute(WebConstant.SESSION_USER_KEY);
		role.setCreatedby(user.getUsercode());
		role.setLastupdatetime(new Date());
		if(roleService.addRole(role)==1){
			return "{\"msg\":1}";
		}
		return "{\"msg\":0}";
	}
	@ResponseBody
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Object getRole(@PathVariable("id") Long id,Role role) {
		System.out.println(role.getId());
		return roleService.getRoleByRole(role);
	}

	@RequestMapping(value = "load/{id}", method = RequestMethod.GET)
	public String loadRole(@PathVariable("id") Long id, Role role,Model model) {
		System.out.println(role.getId());
		role = roleService.getRoleByRole(role);
		System.out.println(role.getRolename());
		model.addAttribute("role", role);
		return "role_edit";
	}
	@ResponseBody
	@RequestMapping(value="{id}/update")
	public Object updateRole(@PathVariable("id") Long id,Role role){
		role.setLastupdatetime(new Date());
		if(roleService.updateRole(role)==1){
			return "{\"msg\":1}";
		}
		return "{\"msg\":0}";
	}
	@ResponseBody
	@RequestMapping(value="delete/{id}")
	public Object deleteRole(@PathVariable("id") Long id,Role role){
		//System.out.println(role.getId());
		if(roleService.deleteRole(role)==1){
			return "{\"msg\":1}";
		}
		return "{\"msg\":0}";
	}
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
}
