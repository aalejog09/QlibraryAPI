package com.api.qlibrary.auxiliar.appUser;

import lombok.Data;

/***
 * 
 * Clase auxiliar para representar atributos publicos de un usuario que va a ser creado.
 * 
 * 
 * @author AAlejo
 *
 */
@Data
public class AppUserCreateDTO {
	
	private String username;
	
	private String email;
	
	private String identificationCode;
	
	private  String firstname;
	
	private  String lastname;
	

}
