package com.api.qlibrary.services.interfaces;


import java.text.ParseException;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.api.qlibrary.auxiliar.author.AuthorDTO;
import com.api.qlibrary.models.Author;

/***
 * Interface para implementar los servicios de Autor.
 * @author AAlejo
 *
 */

public interface IAuthorService {

	/**
	 * Metodo que implementa la consulta de autor por codigo.
	 * @param authorCode
	 * @return Author
	 * @throws Exception
	 */
	public Author getAuthorByCode(@Valid String authorCode) throws Exception;
	
	
	/**
	 * Metodo que implementa la consulta de autor por codigo.
	 * @param authorCode
	 * @return Author
	 * @throws Exception
	 */
	public AuthorDTO getAuthorDataByAuthorCode(@Valid String AuthorCode) throws Exception;

	/**
	 * Metodo que implementa la creacion de autor.
	 * @param authorCode
	 * @return AuthorDTO
	 * @throws Exception
	 */
	public AuthorDTO createAuthor(@Valid AuthorDTO authorDTO) throws ParseException, Exception;
 
	
	/***
	 * Metodo que valida el autor.
	 * @param AuthorCode
	 * @return boolean
	 */
	public boolean validateAuthor(@Valid String AuthorCode);
	
	/***
	 * Metodo que valida el autor.
	 * @param AuthorCode
	 * @return boolean
	 */
	public boolean existAuthorByNameAndLastnameAndCountry(@Valid AuthorDTO authorDTO);

	/***
	 * Metodo que valida el autor.
	 * @param AuthorCode
	 * @return Author
	 */
	public Set<Author> findAllById(String authorCode) throws Exception;

	/***
	 * Metodo que implmenta el servicio de consulta de todos los autores.
	 * @param AuthorCode
	 * @return Author
	 */
	public List<Author> findAll();


	/***
	 * Metodo que implmenta el servicio de consulta de cantidad de autores registrados.
	 * @param AuthorCode
	 * @return Author
	 */
	public Long getTotalAuthors();
}
