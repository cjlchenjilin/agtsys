package org.agtsys.dao;

import java.util.List;

import org.agtsys.domain.Permission;

public interface PermissionMapper {
	public List<Permission> getList(Permission permission);
}