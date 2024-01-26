package com.api.qlibrary.services.interfaces;

import java.text.ParseException;

import javax.validation.Valid;


import com.api.qlibrary.auxiliar.appUser.AppUserCreateDTO;
import com.api.qlibrary.auxiliar.appUser.AppuserDTO;
import com.api.qlibrary.models.Appuser;

/***
 * Interface que implementa los servicios de Usuario.
 * @author AAlejo
 *
 */
public interface IAppuserService {

	/***
	 * Metodo de interface que implementa servicio de consultar usuario por nombre.
	 * @param userName
	 * @return Appuser
	 * @throws Exception
	 */
	public Appuser getAppUserByUsername(String userName) throws Exception;

	
	/***
	 * Metodo de interface que implementa servicio de createAppUser usuario.
	 * @param userName
	 * @return AppuserDTO
	 * @throws Exception
	 */
	public AppuserDTO createAppUser(@Valid AppUserCreateDTO appUser) throws ParseException, Exception;
	
	
}
