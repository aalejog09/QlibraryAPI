package com.api.qlibrary.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.qlibrary.models.Appuser;
import com.api.qlibrary.models.Role;
import com.api.qlibrary.repositories.IAppuserRepository;


@Transactional
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	 private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired 
	IAppuserRepository appuserRepository;
	
	
    @SuppressWarnings("unchecked")
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
     //Buscar el usuario con el repositorio y si no existe lanzar una exepcion
    	Appuser appuser = appuserRepository.findByUsername(username);
		
	    //Mapear nuestra lista de Authority con la de spring security 
	    @SuppressWarnings("rawtypes")
		List grantList = new ArrayList();
	    for (Role userRole: appuser.getRoles()) {
	        // ROLE_USER, ROLE_ADMIN,..
	        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userRole.getRoleName());
	            grantList.add(grantedAuthority);
	    }
			
	    //Crear El objeto UserDetails que va a ir en sesion y retornarlo.
	    UserDetails userDetails = (UserDetails) new User(appuser.getUsername(), appuser.getAccessCode(), grantList);
	         return userDetails;
    }
    
    
       @SuppressWarnings("unchecked")
	public UserDetails loadUserByUsernameAndPassword(String username, String password) throws UsernameNotFoundException {
    	   
        //Buscar el usuario con el repositorio y si no existe lanzar una exepcion
       	Appuser appuser = appuserRepository.findByUsername(username);
   		
       BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();  
       //String encode= bcrypt.encode(password);
 	   boolean isPasswordMatches = bcrypt.matches(password, appuser.getAccessCode()); 
 	   
       logger.info("isPasswordMatches: {}",isPasswordMatches);
 	   if(isPasswordMatches==true){
 		  //Mapear nuestra lista de Authority con la de spring security 
 	   		@SuppressWarnings("rawtypes")
 			List grantList = new ArrayList();
 	   	    for (Role userRole: appuser.getRoles()) {
 	   	        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userRole.getRoleName());
 	   	            grantList.add(grantedAuthority);
 	   	    }
 	   	    //Crear El objeto UserDetails que va a ir en sesion y retornarlo.
 	   	    UserDetails userDetails = (UserDetails) new User(appuser.getUsername(), appuser.getAccessCode(), grantList);
 	   	         return userDetails;
 	       }
 	    	   
 	   return null;
 	   }
   	  
    
}
