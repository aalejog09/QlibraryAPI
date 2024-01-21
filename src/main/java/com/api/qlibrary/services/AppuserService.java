package com.api.qlibrary.services;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.qlibrary.auxiliar.appUser.AppUserCreateDTO;
import com.api.qlibrary.models.Appuser;
import com.api.qlibrary.models.Role;
import com.api.qlibrary.repositories.IAppuserRepository;
import com.api.qlibrary.services.interfaces.IAppuserService;
import com.api.qlibrary.services.interfaces.IEmailService;
import com.api.qlibrary.services.interfaces.IRoleService;
import com.api.qlibrary.util.Constants;
import com.api.qlibrary.util.Utility;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase de métodos de servicios de usuarios.
 * @author Aalejo
 *
 */
@Slf4j
@Service
public class AppuserService implements IAppuserService {
	
	
	/**
	 * Variable que se usa para importar métodos del repositorio de usuarios.
	 */
	@Autowired
	IAppuserRepository appuserRepository;
	
	/**
	 * Variable que se usa para importar métodos de la clase de utilidades.
	 */
	@Autowired
	private Utility utility;
	
	/**
	 * Variable que se usa para importar métodos del servicio de entidades financieras.
	 */
	@Autowired
	IRoleService roleService;
	
	
	/**
	 * Variable que se usa para importar métodos de encriptación de password.
	 */
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	/**
	 * Variable que se usa para importar métodos de encriptación de password.
	 */
	@Autowired
	IEmailService iEmailService;
	
	/**
	 * Método que devuelve un usuario a través del nombre de usuario.
	 * @return Appuser
	 */
	@Override
	public Appuser getAppUserByUsername(String username) throws Exception {
		return appuserRepository.findByUsername(username);
	}
	
	
	/**
	 * Metodo para crear un usuario de tipo Bibliotecario en el sistema.
	 * 
	 * @param appUserDTO
	 * @return Appuser
	 */
	public Appuser createAppUser(AppUserCreateDTO appUserDTO) throws Exception {
		
		boolean emailDomainCheck= utility.validateEmailDomain(appUserDTO.getEmail());
		
		if(emailDomainCheck==false ) { 
			throw new Exception("Correo electronico no cumple con el formato correcto.");
			
		}
		
		if (checkDNIString(appUserDTO.getIdentificationCode()) 
				&& checkEmailString(appUserDTO.getEmail()) 
				&&  checkUsernameString(appUserDTO.getUsername() )) {
			
			log.info("usuario recibido: {}", appUserDTO);		
			
			Set<Role> role = roleService.getRoleById(2);
			Appuser appuser = new Appuser();
			log.info("role {}",role);
			appuser.setIdentificationCode(appUserDTO.getIdentificationCode());
			appuser.setUsername(appUserDTO.getUsername());
			appuser.setFirstname(appUserDTO.getFirstname());
			appuser.setLastname(appUserDTO.getLastname());
			appuser.setEmail(appUserDTO.getEmail());
			appuser.setCreationDate(new Date());
			appuser.setLastEntryDate(null);
			appuser.setRoles(role);
			
			
			String newPassword= utility.passwordGenerator();
			log.info("Nueva contraseña generada",newPassword);
			
			String encodedPassword = bCryptPasswordEncoder.encode(newPassword);
			appuser.setAccessCode(encodedPassword);			
			appuserRepository.save(appuser);
			log.info("usuario creado: {}", appuser);
			appuser.setAccessCode("********");
			
			iEmailService.sendEmail(
					appuser.getEmail(),
					Constants.USER_CREATED_NOTIFICATION ,
					Constants.USER_CREATED
					+"\n User: "+appuser.getUsername()
					+" \n clave: "+newPassword+"\n"+
					Constants.EMAIL_FOOTER);
			
			iEmailService.sendEmail("aalejog09@gmail.com", Constants.USER_CREATED_NOTIFICATION, "Se ha creado el usuario: "+appuser.getUsername()+" para la aplicacion Qlibrary."+Constants.EMAIL_FOOTER);
			return appuser;
	    }
		return null;
	}
	
	/**
	 * Metodo para consultar la disponibilidad de un nombre de usuario.
	 * 
	 * @param username
	 * @return boolean
	 */
	public boolean checkUsernameString(String username) throws Exception {
		boolean userFound = appuserRepository.existsByUsernameIgnoreCase(username);
		if(userFound) {
			throw new Exception("Nombre de usuario no disponible.");
		}
		return true;
	}
	
	/**
	 * Metodo para consultar la disponibilidad de un email de usuario.
	 * 
	 * @param email
	 * @return boolean
	 */
	public boolean checkEmailString(String email) throws Exception {
		
		boolean userFound = appuserRepository.existsByEmailIgnoreCase(email);
		
		if(userFound==true ) { 
			throw new Exception("Correo electronico no disponible.");
		}
		return true;
	}
	
	
	/**
	 * Metodo para consultar la disponibilidad de un codigo de empleado.
	 * 
	 * @param identificationCode
	 * @return boolean
	 */
	public boolean checkDNIString(String identificationCode) throws Exception {
		boolean userFound = appuserRepository.existsByIdentificationCodeIgnoreCase(identificationCode);
		if(userFound) { 
			throw new Exception("Documento de identificacion (DNI) no disponible.");
		}
		return true;
	}
	
	
}
