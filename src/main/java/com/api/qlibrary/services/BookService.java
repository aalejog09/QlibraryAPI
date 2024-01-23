package com.api.qlibrary.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.qlibrary.auxiliar.author.AuthorDTO;
import com.api.qlibrary.auxiliar.book.BookDTO;
import com.api.qlibrary.auxiliar.book.CreateBookDTO;
import com.api.qlibrary.auxiliar.category.CategoryDTO;
import com.api.qlibrary.models.Appuser;
import com.api.qlibrary.models.Author;
import com.api.qlibrary.models.Book;
import com.api.qlibrary.models.Category;
import com.api.qlibrary.repositories.IBookRepository;
import com.api.qlibrary.services.interfaces.IAuthorService;
import com.api.qlibrary.services.interfaces.IBookService;
import com.api.qlibrary.services.interfaces.ICategoryService;
import com.api.qlibrary.util.Utility;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase de métodos de servicios de Libros del sistema.
 * @author Aalejo
 *
 */
@Slf4j
@Service
public class BookService implements IBookService{
	/**
	 * Variable que se usa para importar métodos del repositorio de Libros.
	 */
	@Autowired
	IBookRepository bookRepository;
	
	/**
	 * Variable que se usa para importar métodos de la clase de utilidades.
	 */
	@Autowired
	private Utility utility;
	
	@Autowired
	private IAuthorService iAuthorService;
	
	@Autowired
	private ICategoryService iCategoryService;
	
	/**
	 * Método que recibe los datos para crear un libro.
	 * @return bookDTO libro creado.
	 * @throws Exception 
	 */
	@Override
	public CreateBookDTO createBook(@Valid CreateBookDTO createBookDTO) throws Exception {
		
		
		boolean checkExistingBook= getBookByCode(createBookDTO.getCode());
	
		if(checkExistingBook==false) {
			
			String code=createBookDTO.getCode();
			
			//usuario que realiza la consulta
			Appuser userActive=utility.getActiveUser();
			
			Set<Author> author = iAuthorService.findAllById(createBookDTO.getAuthorCode());
			Set<Category> category = iCategoryService.findAllById(createBookDTO.getCategoryName());
			
			
			Book createdBook = new Book();
			createdBook.setTitle(createBookDTO.getTitle().toUpperCase());
			createdBook.setPublisher(createBookDTO.getPublisher());
			createdBook.setCode(code);
			createdBook.setCreationDate(new Date());
			createdBook.setPublicationDate(utility.StringToDateFormatter(createBookDTO.getPublicationDate()));
			createdBook.setAppuser(userActive);
			createdBook.setAuthors(author);
			createdBook.setCategories(category);
			bookRepository.save(createdBook);
			
			
			return createBookDTO;
			
		}
		log.info("Libro ya existe: {}",createBookDTO);
		throw new Exception("El libro ya se encuentra registrado");
		
	}
	
	
	private boolean getBookByCode(String code) {
		
		Book existBook = bookRepository.findByCode(code);
		
		if(existBook != null) {
			return true;
		}
		return false;
	}


	/**
	 * Método que devuelve todos los libros registrados.
	 * @return Lista(BookListDTO)
	 * @throws Exception 
	 */
	@Override
	public List<BookDTO> getAllBookList() throws Exception {
		
		try {
			
			//Consultar la informacion de la data del libro registrada en la base de datos
			List<Book> lista = bookRepository.findAllByOrderByCreationDateDesc();
			log.info("Lista de libros: {}", lista);
			
			//Instaciar un DTO para mostrar data publica de los libros.
			List<BookDTO> bookDTOs = new ArrayList<>();
			BookDTO bookDTO = new BookDTO();
			
			//Mapear la data publica de los libros.
			for (Book book : lista) {
			   
				bookDTO=mapBookToDTO(book,bookDTO);
			    bookDTOs.add(bookDTO);
			}
			
			return bookDTOs;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception ("No se encontraró la informacion solicitada");
		}
	}

	
	/***
	 * Metodo para mapear la informacion publica del libro.
	 * @param book
	 * @param bookDTO
	 * @return Data publica del libro.
	 * @throws ParseException
	 */
	public BookDTO mapBookToDTO(Book book, BookDTO bookDTO) throws ParseException {
		
		//Instanciar Dtos de Autores
		AuthorDTO authorDTO = new AuthorDTO();
		List<AuthorDTO> authorDTOs = new ArrayList<>();
		
		//Instanciar Dtos de Categorias
		CategoryDTO categoryDTO = new CategoryDTO();
		List<CategoryDTO> categoryDTOs = new ArrayList<>();
		
		//Mapear autores con data publica.
		authorDTOs= utility.mapAuthorDtoList(authorDTOs, authorDTO, book);
		
		//Mapear categorias con data publica.
		categoryDTOs= utility.mapCategoryDtoList(categoryDTOs, categoryDTO, book);
		
		//obtener fecha en formato publico.
		String publicationDate = utility.DateToStringFormatterDash(book.getPublicationDate());
		
		bookDTO.setAuthors(authorDTOs);
	    bookDTO.setCategories(categoryDTOs);
	    bookDTO.setPublicationDate(publicationDate);
	    bookDTO.setTitle(book.getTitle());
	    bookDTO.setPublisher(book.getPublisher());
	    bookDTO.setCode(book.getCode());
		
	    return bookDTO;
			   
	}



	
}
