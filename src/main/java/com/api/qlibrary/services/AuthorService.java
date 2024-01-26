package com.api.qlibrary.services;

import java.util.Date;
import java.util.List;
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
	 * @return AuthorDTO
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
	 * @return AuthorDTO
	 */
	@Override
	public AuthorDTO createAuthor(@Valid AuthorDTO authorDTO) throws Exception {
		
		boolean checkExistingAutor= existAuthorByNameAndLastnameAndCountry(authorDTO);
		log.debug("autor ya existe: {}",checkExistingAutor);
		if(checkExistingAutor==true) {
			log.debug("autor ya existe: {}",authorDTO);
			throw new Exception("El autor ya se encuentra registrado");
		}
		
			Author createdAuthor = new Author();
			createdAuthor=mapAuthordtoToAutor(createdAuthor, authorDTO);
			
			try {
				iAuthorRepository.save(createdAuthor);
				authorDTO.setCode(createdAuthor.getCode());
				return authorDTO;
			} catch (Exception e ) {
				e.printStackTrace();
				
			throw new Exception(e.getMessage());
			}
			
	}
	
	
	/***
	 * Metodo utilitario para mapear los datos de un <Strong> AuthorDTO </Strong> a un <Strong> Author  </Strong>
	 * @param AuthorDTO
	 * @param Author
	 * @return Author
	 */
	public Author mapAuthordtoToAutor(Author createdAuthor, AuthorDTO authorDTO) throws Exception {
		createdAuthor.setName(authorDTO.getName().toUpperCase());
		createdAuthor.setLastname(authorDTO.getLastname().toUpperCase());
		createdAuthor.setCountry(authorDTO.getCountry().toUpperCase());
		createdAuthor.setBirthday(utility.StringToDateFormatter(authorDTO.getBirthday()));
		
		Appuser userActive=utility.getActiveUser();
		createdAuthor.setAppuser(userActive);
		createdAuthor.setCreationDate(new Date());
		
		String authorCode=utility.generatedUniqueCode();
		createdAuthor.setCode(authorCode);
		
		return createdAuthor;
	
	}
	
	/**
	 * Metodo para validar la existencia de un author por su codigo. 
	 * 
	 * @param authorCode
	 * @return boolean
	 */
	public boolean validateAuthor(String authorCode) {
		
		Author author=iAuthorRepository.getAuthorDataByCode(authorCode);
		log.debug("author:{}",author);
		if(author!=null) {
			return true;
		}
		return false;
	}

	/***
	 * Metodo implementado para validar la existencia de un autor.
	 */
	@Override
	public boolean existAuthorByNameAndLastnameAndCountry(@Valid AuthorDTO authorDTO) {
		
		Author author=iAuthorRepository.findByNameAndLastnameAndCountry(authorDTO.getName().toUpperCase(),authorDTO.getLastname().toUpperCase(), authorDTO.getCountry().toUpperCase());
		if (author!=null) {
			return true;
		}
		return false;
	}

	/***
	 * Metodo que implementado para validar la exitencia de un autor.
	 */
	@Override
	public Author getAuthorByCode(@Valid String authorCode) throws Exception {

		Author author=iAuthorRepository.getAuthorDataByCode(authorCode);
		
		if (author!=null) {
			log.debug("autor  existe: {}",author);
			return author;
		}
		throw new Exception("No se encuentra el autor solicitado");
	}

	/***
	 * Metodo que implementado para validar la exitencia de  autores.
	 */
	@Override
	public Set<Author> findAllById(String authorCode) throws Exception {

		Set<Author> author=iAuthorRepository.findAllByCode(authorCode);
		
		if(author.isEmpty()==true) {
			throw new Exception("No se encuentra el autor solicitado.");
		}
		
		
		return author;
	}

	/***
	 * Metodo que implementado para validar la exitencia de  autores.
	 */
	@Override
	public List<Author> findAll() {
		List<Author>  authorList=iAuthorRepository.findAll();
		return authorList;
	}
	
	
	/***
	 * Metodo que implementado para validar la exitencia de  autores.
	 */
	@Override
	public Long getTotalAuthors() {
		Long totalAuthosrs=iAuthorRepository.count();;
		return totalAuthosrs;
	}
	
	
	
}
