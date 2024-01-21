package com.api.qlibrary.services.interfaces;

import java.util.List;
import java.util.Set;

import com.api.qlibrary.models.Role;

public interface IRoleService {

	public List<Role> getAllRoleList() throws Exception;

	public Set<Role> getRoleById(Integer roleId);
}
