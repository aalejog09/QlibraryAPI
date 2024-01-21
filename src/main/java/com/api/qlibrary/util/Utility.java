package com.api.qlibrary.util;

import java.security.SecureRandom;
import java.text.ParseException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.api.qlibrary.models.Appuser;
import com.api.qlibrary.repositories.IAppuserRepository;

/**
 * Clase de métodos utilitarios de la aplicación.
 * @author LPinto
 *
 */
@Service
public class Utility {

	@Autowired
	private Environment env;
	
	/**
	 * Variable que se usa para importar métodos de la libreria de LOGGER.
	 */
	private static final Logger logger = LoggerFactory.getLogger(Utility.class);
	
	/**
	 * Variable que se usa para importar métodos del servicio de usuarios.
	 */
	@Autowired
	private IAppuserRepository appUserRepository;
	
		
	/**
	 * Método que retorna el usuario actual.
	 * @return AppUser
	 * @throws Exception
	 */
	public Appuser getActiveUser() {
	   String  userNameActive = SecurityContextHolder.getContext().getAuthentication().getName() ; 
		
	//declaro usuario para buscar
		logger.info("buscando usuario");
		Appuser userActive = new Appuser();
	try {
		//ubico el usuario por nombre de usuario
		userActive = this.appUserRepository.findByUsername(userNameActive);
		logger.info("userActive : {}",userActive);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	return userActive;
	}
	
	// Metodos de envio de correo.
	
	public void sendMail(String EmailSubject, String EmailBody, String ToAddress) {
		
		logger.info("EmailSubject: {} EmailBody:  {} ToAddress: {}",EmailSubject,EmailBody,ToAddress);
		
		Properties properties =new Properties();
		Session session;
		MimeMessage mimeMessage;
		
		try {
			// Authenticating
			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(
							env.getProperty("mail.smtp.from"), 
							env.getProperty("mail.smtp.from.password")
					);
				}
			};

			properties = new Properties();
			properties.put("mail.smtp.host", env.getProperty("mail.smtp.host"));
			properties.put("mail.smtp.port", env.getProperty("mail.smtp.tls.port"));
			properties.put("mail.smtp.auth", env.getProperty("mail.smtp.auth.status"));
			properties.put("mail.smtp.starttls.enable", env.getProperty("mail.smtp.tls.status"));
			
			logger.debug("properties data: {}",properties);
			// creating session
			session = Session.getInstance(properties, auth);
			// create mimemessage
			mimeMessage = new MimeMessage(session);
			
			//from address should exist in the domain
			mimeMessage.setFrom(new InternetAddress(env.getProperty("mail.smtp.from")));
			mimeMessage.addRecipient(RecipientType.TO, new InternetAddress(ToAddress));
			mimeMessage.setSubject(EmailSubject);
			// setting text message body
			mimeMessage.setContent(
						EmailBody,
						"text/html");
			//mimeMessage.setText(EmailBody);
            // setting HTML message body
			// sending mail
			Transport.send(mimeMessage);
			logger.info("Mail Send Successfully");

		} catch (Exception e) {
			logger.info("Mail Not Send Successfully");
			e.printStackTrace();
		}
	}
	
	
	public String passwordGenerator () throws ParseException {
		logger.info("entrando en PasswordGenerator");
	    
	    String newPasswordGenerated=generateRandomPassword(8);
	    
	    return newPasswordGenerated;
	}
	
	
	public  String generateRandomPassword(int len) throws ParseException{
        // Rango ASCII – alfanumérico (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
 
        // cada iteración del bucle elige aleatoriamente un carácter del dado
        // rango ASCII y lo agrega a la instancia `StringBuilder`
 
        for (int i = 0; i < len; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
    }
	
	
}
