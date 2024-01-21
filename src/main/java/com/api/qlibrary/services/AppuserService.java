package com.api.qlibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.qlibrary.models.Appuser;
import com.api.qlibrary.repositories.IAppuserRepository;
import com.api.qlibrary.services.interfaces.IAppuserService;

/**
 * Clase de m�todos de servicios de usuarios.
 * @author Aalejo
 *
 */
@Service
public class AppuserService implements IAppuserService {

	
	
	/**
	 * Variable que se usa para importar m�todos del repositorio de usuarios.
	 */
	@Autowired
	IAppuserRepository appuserRepository;
	
	
	/**
	 * Variable que se usa para importar m�todos de encriptaci�n de password.
	 */
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	/**
	 * M�todo que devuelve un usuario a trav�s del nombre de usuario.
	 * @return Appuser
	 */
	@Override
	public Appuser getAppUserByUsername(String username) throws Exception {
		return appuserRepository.findByUsername(username);
	}
	
	
	
	


	
}
