package com.api.qlibrary.services.interfaces;


import java.text.ParseException;
import java.util.Set;

import javax.validation.Valid;

import com.api.qlibrary.auxiliar.author.AuthorDTO;
import com.api.qlibrary.models.Author;



public interface IAuthorService {

	
	public Author getAuthorByCode(@Valid String authorCode) throws Exception;
	
	public AuthorDTO getAuthorDataByAuthorCode(@Valid String AuthorCode) throws Exception;

	public AuthorDTO createAuthor(@Valid AuthorDTO authorDTO) throws ParseException, Exception;
 
	public boolean validateAuthor(@Valid String AuthorCode);
	
	public boolean existAuthorByNameAndLastnameAndCountry(@Valid AuthorDTO authorDTO);

	public Set<Author> findAllById(String authorCode) throws Exception;
}
