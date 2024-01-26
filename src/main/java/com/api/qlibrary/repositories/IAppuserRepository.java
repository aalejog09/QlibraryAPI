package com.api.qlibrary.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.qlibrary.models.Appuser;

/**
 * Interfaz para el uso de los métodos de acceso a los datos por JpaRepository de la clase Appuser
 * @author AAlejo
 *
 */
@Repository
public interface IAppuserRepository extends JpaRepository< Appuser, Integer >{

	/**
	 * Método que implementa la busqueda de un usuario con el atributo nombre de usuario.
	 * @param username
	 * @return User
	 */
	public Appuser findByUsername(String username);

	/**
	 * Método que implementa la busqueda de un usuario con su nombre y clave de acceso.
	 * @param username,accessCode
	 * @return User
	 */
	public Appuser findByUsernameAndAccessCode(String username, String accessCode);

	
	/**
	 * Metodo para consultar la disponibilidad de un nombre de usuario.
	 * 
	 * @param username
	 * @return boolean
	 */
	public boolean existsByUsernameIgnoreCase(String username);

	
	/**
	 * Metodo para consultar la disponibilidad de un email de usuario.
	 * 
	 * @param email
	 * @return boolean
	 */
	public boolean existsByEmailIgnoreCase(String email);

	/**
	 * Metodo para consultar la disponibilidad de un codigo de empleado.
	 * 
	 * @param identificationCode
	 * @return boolean
	 */
	public boolean existsByIdentificationCodeIgnoreCase(String identificationCode);




}
