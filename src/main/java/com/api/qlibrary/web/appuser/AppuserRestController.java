package com.api.qlibrary.web.appuser;


import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.qlibrary.auxiliar.appUser.AppUserCreateDTO;
import com.api.qlibrary.models.Appuser;
import com.api.qlibrary.services.UserDetailsServiceImpl;
import com.api.qlibrary.services.interfaces.IAppuserService;
import com.api.qlibrary.util.JwtRequest;
import com.api.qlibrary.util.JwtUtils;
import com.api.qlibrary.util.Utility;

import lombok.extern.slf4j.Slf4j;


/**
 * @author AAlejo
 * 
 * Controlador principal de acceso a la aplicacion. 
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
		try {
			Appuser userCreated = appuserServiceInterface.createAppUser(appUser);
			log.info("Creado usuario rest: {}",appUser);
			if (userCreated == null ) {
				//return new ResponseEntity<>(claim, HttpStatus.UNAUTHORIZED);
				throw new ObjectNotFoundException(null, null);
			}
			
			
			return new ResponseEntity<>(userCreated, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);  //throw new ObjectNotFoundException(null, null); 
		}
	}

    


    
    
}
