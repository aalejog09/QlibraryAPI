package com.api.qlibrary.services;

import java.text.ParseException;
import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.qlibrary.auxiliar.appUser.AppUserCreateDTO;
import com.api.qlibrary.auxiliar.appUser.AppuserDTO;
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
	 * @param appuserData
	 * @return Appuser
	 */
	public AppuserDTO createAppUser(AppUserCreateDTO appuserData) throws Exception {
		log.info("usuario recibido: {}", appuserData);	
		boolean emailDomainCheck= utility.validateEmailDomain(appuserData.getEmail());
		
		if(emailDomainCheck==false ) { 
			throw new Exception("Correo electronico no cumple con el formato correcto.");
			
		}
		
		if (checkDNIString(appuserData.getIdentificationCode()) 
				&& checkEmailString(appuserData.getEmail()) 
				&&  checkUsernameString(appuserData.getUsername() )) {
			
				
			
			//instancia del modelo user.
			Appuser appuser = new Appuser();
			//mapear datos a registrar.
			appuser = mapAppuserdtoToAppuser(appuser, appuserData);
			
			//instancia del DTO a devolver.
			AppuserDTO appuserDTO = new AppuserDTO();
			
			try {
				//guardar usuario.
				appuserRepository.save(appuser);
				log.info("usuario creado: {}", appuser);
				
				//mapear usuario.
				appuserDTO= mapAppuserToDTO(appuser,appuserDTO);
				
				//enviar correos de notificacion.
				log.info("enviado correo electronico de notificacion al usuario.");
				iEmailService.sendEmail(
						appuser.getEmail(),
						Constants.USER_CREATED_NOTIFICATION ,
						Constants.USER_CREATED
						+"\n User: "+appuser.getUsername()
						+" \n clave: "+appuser.getAccessCode()+"\n"+
						Constants.EMAIL_FOOTER);
				log.info("enviado correo electronico de notificacion al administrador del sistema..");
				iEmailService.sendEmail("aalejog09@gmail.com", Constants.USER_CREATED_NOTIFICATION, "Se ha creado el usuario: "+appuser.getUsername()+" para la aplicacion Qlibrary."+Constants.EMAIL_FOOTER);
				log.info("Correos enviados exitosamente.");
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("Ocurrio un error al intentar registrar al usuario"+e.getMessage());
				
				
			}
			
			return appuserDTO;
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
	
	public AppuserDTO mapAppuserToDTO(Appuser appuser, AppuserDTO appuserDTO) {
		
		appuserDTO.setEmail(appuser.getEmail());
		appuserDTO.setFirstname(appuser.getFirstname());
		appuserDTO.setLastname(appuser.getLastname());
		appuserDTO.setIdentificationCode(appuser.getIdentificationCode());
		appuserDTO.setUsername(appuser.getUsername());
		
		return appuserDTO;
	}
	
	public Appuser mapAppuserdtoToAppuser( Appuser appuser, AppUserCreateDTO appuserData) throws ParseException {
		
		appuser.setIdentificationCode(appuserData.getIdentificationCode());
		appuser.setUsername(appuserData.getUsername().toLowerCase());
		appuser.setFirstname(appuserData.getFirstname());
		appuser.setLastname(appuserData.getLastname());
		appuser.setEmail(appuserData.getEmail());
		
		String newPassword= utility.passwordGenerator();
		String encodedPassword = bCryptPasswordEncoder.encode(newPassword);
		
		appuser.setAccessCode(encodedPassword);	
		appuser.setCreationDate(new Date());
		appuser.setLastEntryDate(null);
		Set<Role> role = roleService.getRoleById(2);
		appuser.setRoles(role);
		
		
		return appuser;
		
		
	}
	
	
	
}
