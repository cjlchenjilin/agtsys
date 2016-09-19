package org.agtsys.service;

import java.util.List;
import org.agtsys.domain.Permission;

public interface IPermissionService {
	List<Permission> getPermissionByRole(Permission permission) ;
}
