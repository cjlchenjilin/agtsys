package org.agtsys.service;

import java.util.List;

import org.agtsys.dao.PermissionMapper;
import org.agtsys.domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PermissionService implements IPermissionService {
	@Autowired
	private PermissionMapper permissionMapper;
	@Override
	public List<Permission> getPermissionByRole(Permission permission) {
		return permissionMapper.getList(permission);
	}
	public void setPermissionMapper(PermissionMapper permissionMapper) {
		this.permissionMapper = permissionMapper;
	}

}
