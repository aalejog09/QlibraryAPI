package com.api.qlibrary.services.interfaces;

import com.api.qlibrary.models.Appuser;

public interface IAppuserService {

	public Appuser getAppUserByUsername(String userName) throws Exception;
	
	
}
