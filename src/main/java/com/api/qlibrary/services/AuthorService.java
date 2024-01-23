package com.api.qlibrary.services;

import java.util.Date;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.qlibrary.auxiliar.author.AuthorDTO;
import com.api.qlibrary.models.Appuser;
import com.api.qlibrary.models.Author;
import com.api.qlibrary.repositories.IAuthorRepository;
import com.api.qlibrary.services.interfaces.IAuthorService;
import com.api.qlibrary.services.interfaces.IEmailService;
import com.api.qlibrary.util.Constants;
import com.api.qlibrary.util.Utility;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase de métodos de servicios de autores.
 * @author Aalejo
 *
 */
@Slf4j
@Service
public class AuthorService implements IAuthorService {
	
	/**
	 * Variable que se usa para importar métodos del servicio de usuarios.
	 */
	@Autowired
	IAuthorRepository iAuthorRepository;
	
	
	/**
	 * Variable que se usa para importar métodos de la clase de utilidades.
	 */
	@Autowired
	private Utility utility;
	
	/**
	 * Variable que se usa para importar métodos de encriptación de password.
	 */
	@Autowired
	IEmailService iEmailService;
	
	
	
	/**
	 * Metodo ubicar un Autor en el sistema por su codigo.
	 * 
	 * @param authorCode
	 * @return Author
	 */
	@Override
	public AuthorDTO getAuthorDataByAuthorCode(String authorCode) throws Exception {
		AuthorDTO foundAuthor=new AuthorDTO();
		Author author= iAuthorRepository.getAuthorDataByCode(authorCode);
		if(author==null) {
			throw new Exception (Constants.NOT_FOUND_OBJECT);
		}
		foundAuthor.setName(author.getName());
		foundAuthor.setLastname(author.getLastname());
		foundAuthor.setCode(author.getCode());
		foundAuthor.setBirthday(utility.DateToStringFormatterDash(author.getBirthday()));
		foundAuthor.setCountry(author.getCountry());
		
		return foundAuthor;
	}

	/**
	 * Metodo crear un Autor en el sistema.
	 * 
	 * @param authorDTO
	 * @return Author
	 */
	@Override
	public AuthorDTO createAuthor(@Valid AuthorDTO authorDTO) throws Exception {
		
		
		boolean checkExistingAutor= existAuthorByNameAndLastnameAndCountry(authorDTO);
		String authorCode=utility.generatedUniqueCode();
		log.info("authorCode: {}",authorCode);
		
		if(checkExistingAutor==false) {
			//usuario que realiza la consulta
			Appuser userActive=utility.getActiveUser();
			
			Author createdAuthor = new Author();
			createdAuthor.setName(authorDTO.getName().toUpperCase());
			createdAuthor.setLastname(authorDTO.getLastname().toUpperCase());
			createdAuthor.setCountry(authorDTO.getCountry().toUpperCase());
			createdAuthor.setBirthday(utility.StringToDateFormatter(authorDTO.getBirthday()));
			createdAuthor.setCode(authorCode);
			createdAuthor.setAppuser(userActive);
			createdAuthor.setCreationDate(new Date());
			authorDTO.setCode(authorCode);
			iAuthorRepository.save(createdAuthor);
			
			return authorDTO;
			
		}
		log.info("autor ya existe: {}",authorDTO);
		throw new Exception("El autor ya se encuentra registrado");
		
	}
	
	
	/**
	 * Metodo para validar la existencia de un author por su codigo. 
	 * 
	 * @param authorCode
	 * @return boolean
	 */
	public boolean validateAuthor(String authorCode) {
		
		Author author=iAuthorRepository.getAuthorDataByCode(authorCode);
		log.info("author:{}",author);
		if(author!=null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean existAuthorByNameAndLastnameAndCountry(@Valid AuthorDTO authorDTO) {
		
		Author author=iAuthorRepository.findByNameAndLastnameAndCountry(authorDTO.getName(),authorDTO.getLastname(), authorDTO.getCountry());
		if (author!=null) {
			return true;
		}
		return false;
	}

	@Override
	public Author getAuthorByCode(@Valid String authorCode) throws Exception {

		Author author=iAuthorRepository.getAuthorDataByCode(authorCode);
		
		if (author!=null) {
			log.info("autor  existe: {}",author);
			return author;
		}
		throw new Exception("No se encuentro el autor");
	}

	@Override
	public Set<Author> findAllById(String authorCode) {

		Set<Author> author=iAuthorRepository.findAllByCode(authorCode);
		return author;
	}
	
}
