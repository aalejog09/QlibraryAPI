package com.api.qlibrary.util;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.api.qlibrary.services.AppuserService;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase de métodos utilitarios de la aplicación.
 * @author LPinto
 *
 */
@Service
@Slf4j
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
	
	public boolean validateEmailDomain(String email) {
		boolean validated= false;
		Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	    Matcher matcher = pattern.matcher(email);

	    if (matcher.find() == true) {
	       log.info("Format Valid");
	        validated= true;
	    } else {
	    	log.info("Not Format Invalid");
	    }
		
		return validated;
		
	}
	
	/**
	 * Metodo para transformar una fecha tipo Date en una fecha String formato DD-MM-YYYY
	 * @param date
	 * @return DD-MM-YYYY
	 * @throws ParseException
	 */
	public String DateToStringFormatterDash (Date date) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Integer day = calendar.get(Calendar.DAY_OF_MONTH);
		Integer month = calendar.get(Calendar.MONTH)+1;
		Integer year = calendar.get(Calendar.YEAR);
		String dayString;
		String monthString;
		if (day < 10) {
			dayString = 0+day.toString();
		}else {
			dayString = day.toString();
		}
		if (month < 10) {
			monthString = 0+month.toString();
		}else {
			monthString = month.toString();
		}			
		String dateFormated = dayString+"-"+monthString+"-"+year.toString();
		return dateFormated;
	}
	
	
	/**
	 * Metodo para transformar una fecha tipo Date en una fecha String formato DDMMYYYYHHMMSS
	 * @param date
	 * @return DD-MM-YYYY
	 * @throws ParseException
	 */
	public String DateToStringFormatterNoDash (Date date) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Integer day = calendar.get(Calendar.DAY_OF_MONTH);
		Integer month = calendar.get(Calendar.MONTH)+1;
		Integer year = calendar.get(Calendar.YEAR);
		String dayString;
		String monthString;
		if (day < 10) {
			dayString = 0+day.toString();
		}else {
			dayString = day.toString();
		}
		if (month < 10) {
			monthString = 0+month.toString();
		}else {
			monthString = month.toString();
		}			
		String dateFormated = dayString+monthString+year.toString();
		return dateFormated;
	}
	
	
	/**
	 * Metodo que convierte una fecha tipo String a una fecha Date.
	 * @param dateString
	 * @return dateFormated
	 * @throws ParseException
	 */
	public Date StringToDateFormatter (String dateString) throws ParseException {
		//logger.info("entrando en StringToDateFormatter con date: {}",dateString );
//		dateString = StringDateFormatter(dateString);
	    SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy");  
	   // logger.info("dateString: {}",dateString);
		Date dateFormated = formatter.parse(dateString);
		logger.info("saliendo en StringToDateFormatter con date: {}",dateString );
		return dateFormated;
	}
	
}
