package com.api.qlibrary.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.qlibrary.models.Role;
import com.api.qlibrary.repositories.IRoleRepository;
import com.api.qlibrary.services.interfaces.IRoleService;

/**
 * Clase de métodos de servicios de roles de usuarios.
 * @author Aalejo
 *
 */
@Service
public class RoleService implements IRoleService{

	/**
	 * Variable que se usa para importar métodos del repositorio de roles.
	 */
	@Autowired
	IRoleRepository roleRepository;
	
	
	/**
	 * Método que devuelve todos los roles.
	 * @return Lista(EventActivity)
	 * @throws Exception 
	 */
	@Override
	public List<Role> getAllRoleList() throws Exception {
		List<Role> list = roleRepository.findAll();
		if (list==null||list.isEmpty()) {
			throw new Exception ("No se pudo obtener los roles del sistema");
		}
		return list;
	}

	

	/**
	 * Método que devuelve un rol a través del id de rol.
	 * @return AppUser
	 */
	@Override
	public Set<Role> getRoleById(Integer code)  {
		return roleRepository.findAllById(code);
	}
	
}
