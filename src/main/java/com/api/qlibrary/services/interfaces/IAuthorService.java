package com.api.qlibrary.services.interfaces;


import java.text.ParseException;

import javax.validation.Valid;

import com.api.qlibrary.auxiliar.author.AuthorDTO;



public interface IAuthorService {

	public AuthorDTO getAuthorDataByAuthorCode(@Valid String AuthorCode) throws Exception;

	public AuthorDTO createAuthor(@Valid AuthorDTO authorDTO) throws ParseException, Exception;
 
	public boolean validateAuthor(@Valid String AuthorCode);
	
	public boolean existAuthorByNameAndLastnameAndCountry(@Valid AuthorDTO authorDTO);
}
