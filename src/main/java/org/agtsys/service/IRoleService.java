package org.agtsys.service;

import java.util.List;

import org.agtsys.domain.Role;
import org.agtsys.util.PageTool;

public interface IRoleService {
	Integer getTotalCount();
    List<Role> getPagedRoles(PageTool pt);
    List<Role> getAllRoles();
    Role getRoleByRole(Role role);
    Integer addRole(Role role);
    Integer updateRole(Role role);
    Integer deleteRole(Role role);
}
