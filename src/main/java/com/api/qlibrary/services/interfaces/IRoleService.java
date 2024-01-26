package com.api.qlibrary.services.interfaces;

import java.util.List;
import java.util.Set;

import com.api.qlibrary.models.Role;


/**
 * Interface que implementa el servicio de roles.
 * @author AAlejo
 *
 */
public interface IRoleService {

	/***
	 * Metodo que obtiene la lista de todos los roles.
	 * @return Role list
	 * @throws Exception
	 */
	public List<Role> getAllRoleList() throws Exception;

	/**
	 * Metodo que implementa la consulta del rol por su id.
	 * @param roleId
	 * @return
	 */
	public Set<Role> getRoleById(Integer roleId);
}
