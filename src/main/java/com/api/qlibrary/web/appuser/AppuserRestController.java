package com.api.qlibrary.web.appuser;


import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.qlibrary.auxiliar.appUser.AppUserCreateDTO;
import com.api.qlibrary.auxiliar.appUser.AppuserDTO;
import com.api.qlibrary.services.interfaces.IAppuserService;

import lombok.extern.slf4j.Slf4j;


/**
 * @author AAlejo
 * 
 * Controlador de los servicios de usuario.
 * */
@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/qlibray/api/v1/user")
public class AppuserRestController {

	
	/**
	 * Variable que se usa para importar métodos del servicio de usuarios.
	 */
	@Autowired
	IAppuserService appuserServiceInterface;
	
	
	/**
	 * Método de controlador que llama servicio que crea un usuario
	 * @return AppUser
	 * @throws Exception
	 */
	@PostMapping(value="/create")
	public ResponseEntity<?> createAppUser(@RequestBody @Valid AppUserCreateDTO appUser) throws Exception {
		log.info("data recibida: {}",appUser.toString());
		Map<String,Object> response = new HashMap<String,Object>();
		try {
			AppuserDTO userCreated = appuserServiceInterface.createAppUser(appUser);
			log.info("Creado usuario rest: {}",appUser);
			if (userCreated == null ) {
				throw new ObjectNotFoundException(null, null);
			}
			response.put("status","OK");
			response.put("message","Usuario creado exitosamente. Verifique el correo electronico proporcionado para conocer su contraseña.");
			response.put("userCreated", appUser);
			
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("status","Error");
			response.put("message",e.getMessage());
			return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE);  //throw new ObjectNotFoundException(null, null); 
		}
	}

    


    
    
}
