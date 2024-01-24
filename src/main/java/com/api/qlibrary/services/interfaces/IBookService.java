package com.api.qlibrary.services.interfaces;

import java.util.List;

import javax.validation.Valid;

import com.api.qlibrary.auxiliar.book.AuthorBookDTO;
import com.api.qlibrary.auxiliar.book.BookConsultDTO;
import com.api.qlibrary.auxiliar.book.BookResponseDTO;
import com.api.qlibrary.auxiliar.book.CategoryBookDTO;
import com.api.qlibrary.auxiliar.book.CreateBookDTO;

public interface IBookService {

	
	/***
	 * Interfase que implementa el servicio de consulta de todos los libros. 
	 * 
	 * 
	 * @return Lista de libros registrados.
	 */
	public List<BookResponseDTO> getAllBookList() throws Exception;


	/***
	 * Interfase que implementa el servicio de creacion libros. 
	 * @param bookDTO
	 * 
	 * @return libro creado.
	 */
	public CreateBookDTO createBook(@Valid CreateBookDTO bookDTO) throws Exception;
	
	public BookResponseDTO addCategoryToBook(@Valid CategoryBookDTO data) throws Exception;

	public BookResponseDTO addAuthorToBook(@Valid AuthorBookDTO data) throws Exception;


	public BookResponseDTO getBookInfoByCode(BookConsultDTO bookDTO) throws Exception;


	public List<BookResponseDTO> getBookInfoByCategory(BookConsultDTO bookDTO) throws Exception;

}
