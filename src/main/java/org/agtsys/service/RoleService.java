package org.agtsys.service;

import java.util.List;

import org.agtsys.dao.RoleMapper;
import org.agtsys.domain.Role;
import org.agtsys.util.PageTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RoleService implements IRoleService {
	@Autowired
	private RoleMapper roleMapper;
	@Override
	public List<Role> getAllRoles() {
		return roleMapper.getAllRoles();
	}
	public void setRoleMapper(RoleMapper roleMapper) {
		this.roleMapper = roleMapper;
	}
	@Override
	public Integer getTotalCount() {
		return roleMapper.getTotalCount();
	}
	@Override
	public List<Role> getPagedRoles(PageTool pt) {
		// TODO Auto-generated method stub
		return roleMapper.getPagedRoles(pt);
	}
	@Override
	public Role getRoleByRole(Role role) {
		// TODO Auto-generated method stub
		return roleMapper.getRoleByRole(role);
	}
	@Override
	public Integer addRole(Role role) {
		// TODO Auto-generated method stub
		return roleMapper.addRole(role);
	}
	@Override
	public Integer updateRole(Role role) {
		// TODO Auto-generated method stub
		return roleMapper.updateRole(role);
	}
	@Override
	public Integer deleteRole(Role role) {
		// TODO Auto-generated method stub
		return roleMapper.deleteRole(role);
	}

}
