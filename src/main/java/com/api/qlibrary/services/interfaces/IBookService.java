package com.api.qlibrary.services.interfaces;

import java.util.List;

import javax.validation.Valid;

import com.api.qlibrary.auxiliar.book.BookDTO;
import com.api.qlibrary.auxiliar.book.CreateBookDTO;

public interface IBookService {

	
	/***
	 * Interfase que implementa el servicio de consulta de todos los libros. 
	 * 
	 * 
	 * @return Lista de libros registrados.
	 */

	public List<BookDTO> getAllBookList() throws Exception;


	public CreateBookDTO createBook(@Valid CreateBookDTO bookDTO) throws Exception;

}
