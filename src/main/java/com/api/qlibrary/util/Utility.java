package com.api.qlibrary.util;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.api.qlibrary.auxiliar.author.AuthorDTO;
import com.api.qlibrary.auxiliar.category.CategoryDTO;
import com.api.qlibrary.models.Appuser;
import com.api.qlibrary.models.Author;
import com.api.qlibrary.models.Book;
import com.api.qlibrary.models.Category;
import com.api.qlibrary.repositories.IAppuserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase de métodos utilitarios de la aplicación.
 * @author LPinto
 *
 */
@Service
@Slf4j
public class Utility {
	
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
	
	
	/***
	 * Metodo para mapear un objeto Author a otro AuthorDTO
	 * @param author
	 * @param authorDTO
	 * @return el Author mapeado.
	 * @throws ParseException
	 */
	public AuthorDTO mapAuthorToAuthorDTO(Author author, AuthorDTO authorDTO) throws ParseException {
		
		authorDTO.setName(author.getName());
		authorDTO.setLastname(author.getLastname());
		authorDTO.setCountry(author.getCountry());
		authorDTO.setCode(author.getCode());
		String authorBirthday = DateToStringFormatterDash(author.getBirthday());
		authorDTO.setBirthday(authorBirthday);
		
		return authorDTO;
		
	}
	
	/***
	 * Metodo para mapear una lista de datos de autor
	 * @param authorDTOs
	 * @param authorDTO
	 * @param book
	 * @return lista con data publica.
	 * @throws ParseException
	 */
	public List<AuthorDTO> mapAuthorDtoList(List<AuthorDTO> authorDTOs, AuthorDTO authorDTO, Book book) throws ParseException {
		Set<Author> authors = book.getAuthors();
		
		for (Author author : authors) {
		    
			authorDTO= 	mapAuthorToAuthorDTO(author, authorDTO);
			
			authorDTOs.add(authorDTO);
			}
		return authorDTOs;
		
	}
	
	/***
	 * Metodo para mapear la data de la categoria. 
	 * @param category
	 * @param categoryDTO
	 * @return la categoria mapeada.
	 * @throws ParseException
	 */
	public CategoryDTO mapCategoryToCategoryDTO(Category category, CategoryDTO categoryDTO) throws ParseException {
		
		categoryDTO.setName(category.getName());
		
		return categoryDTO;
		
	}
	
	/***
	 * Metodo para mapear una lista de datos de categorias
	 * @param categoryDTOs
	 * @param categoryDTO
	 * @param book
	 * @return lista con data publica.
	 * @throws ParseException
	 */
	public List<CategoryDTO> mapCategoryDtoList(List<CategoryDTO> categoryDTOs, CategoryDTO categoryDTO, Book book) throws ParseException {
		Set<Category> categories = book.getCategories();
		
		for (Category category : categories) {
		    
			categoryDTO= mapCategoryToCategoryDTO(category, categoryDTO);
			
			categoryDTOs.add(categoryDTO);
			
			}
		
		return categoryDTOs;
		
	}
	
	
	/***
	 * Metodo que genera un codigo unico haciendo uso del momento exacto de su creacion.
	 * 
	 * 
	 * @return code
	 * @throws Exception
	 */
	public String generatedUniqueCode() throws Exception {
		
		
		String dateString=DateToStringFormatterNoDash(new Date());
		   LocalTime horaActual = LocalTime.now();
	        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HHmmss");
	        String horaFormateada = horaActual.format(formato);
		log.info("horaFormateada: {}",horaFormateada);
		
		String code=dateString+horaFormateada;
		return code;
		
	}
	
	
}
