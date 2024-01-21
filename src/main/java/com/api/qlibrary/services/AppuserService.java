package com.api.qlibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.qlibrary.models.Appuser;
import com.api.qlibrary.repositories.IAppuserRepository;
import com.api.qlibrary.services.interfaces.IAppuserService;

/**
 * Clase de métodos de servicios de usuarios.
 * @author Aalejo
 *
 */
@Service
public class AppuserService implements IAppuserService {

	
	
	/**
	 * Variable que se usa para importar métodos del repositorio de usuarios.
	 */
	@Autowired
	IAppuserRepository appuserRepository;
	
	
	/**
	 * Variable que se usa para importar métodos de encriptación de password.
	 */
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	/**
	 * Método que devuelve un usuario a través del nombre de usuario.
	 * @return Appuser
	 */
	@Override
	public Appuser getAppUserByUsername(String username) throws Exception {
		return appuserRepository.findByUsername(username);
	}
	
	
	
	


	
}
