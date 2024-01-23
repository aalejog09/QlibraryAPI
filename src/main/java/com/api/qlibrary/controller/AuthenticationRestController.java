package com.api.qlibrary.controller;


import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.api.qlibrary.models.Appuser;
import com.api.qlibrary.services.UserDetailsServiceImpl;
import com.api.qlibrary.services.interfaces.IAppuserService;
import com.api.qlibrary.util.JwtRequest;
import com.api.qlibrary.util.JwtUtils;
import com.api.qlibrary.util.Utility;


/**
 * @author AAlejo
 * 
 * Controlador principal de acceso a la aplicacion. 
 * */
@RestController
@CrossOrigin("*")
@RequestMapping("/qlibray/api/v1/auth")
public class AuthenticationRestController {
    
	@Autowired
    public AuthenticationManager authenticationManager;
    
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    
    @Autowired
    private IAppuserService appuserService;
    
    @Autowired
    private JwtUtils jwtUtils;
    
    public Utility util;
    
    public BCryptPasswordEncoder bCryptPasswordEncoder;
    
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationRestController.class);

    
	@PostMapping("/requestToken")
	public Map<String, Object> login(@RequestBody JwtRequest jwtRequest) throws Exception {

		logger.info("request token incoming ", jwtRequest.getUsername());
		Map<String, Object> userResponse = new HashMap<String, Object>();
		try {
			autenticar(jwtRequest.getUsername(), jwtRequest.getPassword());

		} catch (Exception exception) {
			logger.info("Acceso denegado - Credenciales Invalidas: {}", jwtRequest);
		}
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsernameAndPassword(jwtRequest.getUsername(),jwtRequest.getPassword());
		if(userDetails!=null) {
			String token = this.jwtUtils.generateToken(userDetails);
			Appuser userActive = this.appuserService.getAppUserByUsername(userDetails.getUsername());
			
			logger.debug("usuario identificado : {}", userActive);
			userResponse.put("Username", userActive.getUsername());
			userResponse.put("email", userActive.getEmail());
			userResponse.put("IdentificationCode", userActive.getIdentificationCode());
			userResponse.put("Auth", token);
			logger.debug("Usuario Logeado con exito.");
			return userResponse;
		}
		userResponse.put("Error","Acceso no autorizado.");
		userResponse.put("Auth", "null");
		return userResponse ;

	}

	private void autenticar(String username,String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (BadCredentialsException e){
        	logger.info("Credenciales invalidas");
            throw  new Exception("Credenciales invalidas " + e.getMessage());
        }      
    }

    @GetMapping("/userLogged")
    public Appuser obtenerUsuarioActual(Principal principal) throws Exception{
        return  this.appuserService.getAppUserByUsername(principal.getName());
    }
    
    
    
}
