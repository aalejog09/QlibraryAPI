package com.api.qlibrary.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.qlibrary.models.Role;

/**
 * Interfaz para el uso de los métodos de acceso a los datos por JPA/HIBERNATE de la clase Role
 * @author AAlejo
 *
 */
@Repository
public interface IRoleRepository extends JpaRepository< Role, Integer >{

	/**
	 * Metodo de consulta que implementa la busqueda de todos los roles por el id
	 * @param code
	 * @return Role
	 */
	public Set<Role> findAllById(Integer code);

	
	/**
	 * Metodo de consulta que implementa la busqueda de un rol por su nombre
	 * @param code
	 * @return Role
	 */
	public boolean existsByRoleNameIgnoreCase(String roleName);

}
