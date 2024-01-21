package com.api.qlibrary.services.interfaces;

import java.text.ParseException;

import javax.validation.Valid;


import com.api.qlibrary.auxiliar.appUser.AppUserCreateDTO;
import com.api.qlibrary.models.Appuser;

public interface IAppuserService {

	public Appuser getAppUserByUsername(String userName) throws Exception;

	public Appuser createAppUser(@Valid AppUserCreateDTO appUser) throws ParseException, Exception;
	
	
}
