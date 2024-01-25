package com.api.qlibrary.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.qlibrary.auxiliar.author.AuthorDTO;
import com.api.qlibrary.auxiliar.book.AuthorBookDTO;
import com.api.qlibrary.auxiliar.book.BookConsultDTO;
import com.api.qlibrary.auxiliar.book.BookResponseDTO;
import com.api.qlibrary.auxiliar.book.CategoryBookDTO;
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
 * 
 * @author Aalejo
 *
 */
@Slf4j
@Service
public class BookService implements IBookService {
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
	 * 
	 * @return bookDTO libro creado.
	 * @throws Exception
	 */
	@Override
	public CreateBookDTO createBook(@Valid CreateBookDTO createBookDTO) throws Exception {

		boolean checkExistingBook = getBookByCode(createBookDTO.getCode());

		if (checkExistingBook == false) {

			String code = createBookDTO.getCode();

			// usuario que realiza la consulta
			Appuser userActive = utility.getActiveUser();

			Set<Author> author = iAuthorService.findAllById(createBookDTO.getAuthorCode());
			Set<Category> category = iCategoryService.findAllById(createBookDTO.getCategoryName());
			if (category.isEmpty() == true) {
				throw new Exception("No se encontro la categoria solicitada.");
			}

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
		log.info("Libro ya existe: {}", createBookDTO);
		throw new Exception("El libro ya se encuentra registrado");

	}

	private boolean getBookByCode(String code) {

		Book existBook = bookRepository.findByCode(code);

		if (existBook != null) {
			return true;
		}
		return false;
	}

	/**
	 * Método que devuelve todos los libros registrados.
	 * 
	 * @return Lista(BookListDTO)
	 * @throws Exception
	 */
	@Override
	public List<BookResponseDTO> getAllBookList() throws Exception {

		try {

			// Consultar la informacion de la data del libro registrada en la base de datos
			List<Book> lista = bookRepository.findAllByOrderByCreationDateDesc();
			log.info("Lista de libros: {}", lista);

			// Instaciar un DTO para mostrar data publica de los libros.
			List<BookResponseDTO> bookDTOs = new ArrayList<>();

			
//			//Mapear la data publica de los libros.
			for (Book book : lista) {
				// instanciar el objeto nuevo.
				BookResponseDTO bookDTO = new BookResponseDTO();
				log.info("Book : {}", book);

				bookDTO = mapBookToDTO(book, bookDTO);
				bookDTOs.add(bookDTO);
			}
//			
			return bookDTOs;
//			return lista;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("No se encuentra la informacion solicitada");
		}
	}

	/***
	 * Metodo para mapear la informacion publica del libro.
	 * 
	 * @param book
	 * @param bookDTO
	 * @return Data publica del libro.
	 * @throws ParseException
	 */
	public BookResponseDTO mapBookToDTO(Book book, BookResponseDTO bookDTO) throws ParseException {

		// Instanciar Dtos de Autores
		List<AuthorDTO> authorDTOs = new ArrayList<>();

		// Instanciar Dtos de Categorias
		List<CategoryDTO> categoryDTOs = new ArrayList<>();

		// Mapear autores con data publica.
		authorDTOs = utility.mapAuthorDtoList(authorDTOs,  book);

		// Mapear categorias con data publica.
		categoryDTOs = utility.mapCategoryDtoList(categoryDTOs,  book);

		// obtener fecha en formato publico.
		String publicationDate = utility.DateToStringFormatterDash(book.getPublicationDate());

		bookDTO.setAuthors(authorDTOs);
		bookDTO.setCategories(categoryDTOs);
		bookDTO.setPublicationDate(publicationDate);
		bookDTO.setTitle(book.getTitle());
		bookDTO.setPublisher(book.getPublisher());
		bookDTO.setCode(book.getCode());

		return bookDTO;

	}
	
	
	public BookResponseDTO addCategoryToBook(CategoryBookDTO data) throws Exception {
		
			Set<Category> newCategory = new HashSet<Category>();
			Book book= new Book();
			Set<Category> function = new HashSet<Category>();
			
			function = iCategoryService.findAllById(data.getCategoryName().toUpperCase());
			book = findBookByCode(data.getCode());
			
			newCategory.addAll(book.getCategories());
			newCategory.addAll(function);
			book.setCategories(newCategory);
			bookRepository.save(book);
			log.info("rol modificado: {}", book);
			
			BookResponseDTO bookDTO = new BookResponseDTO();
			bookDTO= mapBookToDTO(book, bookDTO);
			
			return bookDTO;
		
	}
	
	@Override
	public BookResponseDTO addAuthorToBook(@Valid AuthorBookDTO data) throws Exception {

		Set<Author> newAuthor = new HashSet<Author>();
		Book book= new Book();
		Set<Author> author = new HashSet<Author>();
		
		author = iAuthorService.findAllById(data.getAuthorCode());
		book = findBookByCode(data.getCode());
		
		newAuthor.addAll(book.getAuthors());
		newAuthor.addAll(author);
		book.setAuthors(newAuthor);
		bookRepository.save(book);
		log.info("rol modificado: {}", book);
		
		BookResponseDTO bookDTO = new BookResponseDTO();
		bookDTO= mapBookToDTO(book, bookDTO);
		
		return bookDTO;
		
	}
	
	
	private Book findBookByCode(String code) throws Exception {

		Book existBook = bookRepository.findByCode(code);
		if (existBook==null) {
			throw new Exception("No se encuentra el libro solicitado.");
		}
		return existBook;
		
	}
	
	/***
	 * Metodo para consultar la informacion de un libro mediante su codigo.
	 * 
	 * @param BookConsultDTO
	 * 
	 * @return BookResponseDTO
	 */
	@Override
	public BookResponseDTO getBookInfoByCode(BookConsultDTO bookDTO) throws Exception {
		
		Book existBook = bookRepository.findByCode(bookDTO.getCode());
		log.info("book: {}",existBook);
		if (existBook==null) {
			throw new Exception("No se encuentra el libro solicitado.");
		}
		
		BookResponseDTO bookResponse = new BookResponseDTO();
		bookResponse = mapBookToDTO(existBook, bookResponse);
		return bookResponse;
	}

	/***
	 * Metodo para consultar la informacion de un libro mediante su categoria.
	 * 
	 * @param BookConsultDTO
	 * 
	 * @return List<BookResponseDTO>
	 */
	@Override
	public List<BookResponseDTO> getBookInfoByCategory(BookConsultDTO bookDTO) throws Exception {
		List<Book> existBook = bookRepository.findByCategoriesId(Integer.valueOf(bookDTO.getCode()));
		log.info("book: {}",existBook);
		if (existBook.isEmpty()) {
			throw new Exception("No se encuentra la informacion solicitada.");
		}
		
		List<BookResponseDTO> bookListDTO = new ArrayList<BookResponseDTO>();
		
		bookListDTO= mapBookListToDto(existBook);
		
		return bookListDTO;
	}
	
	
	@Override
	public List<BookResponseDTO> getBookInfoByAuthor(BookConsultDTO bookDTO) throws Exception {
		
		Author author=iAuthorService.getAuthorByCode(bookDTO.getCode());
		List<BookResponseDTO> bookListDTO = new ArrayList<BookResponseDTO>();
		if(author!=null) {
			
			List<Book> existBook = bookRepository.findByAuthorsId(author.getId());
			log.info("book: {}",existBook);
			if (existBook.isEmpty()) {
				throw new Exception("No se encuentra la informacion solicitada.");
			}
			
			bookListDTO= mapBookListToDto(existBook);
			
		}
		
		
		return bookListDTO;
	}
	
	
	public List<BookResponseDTO> mapBookListToDto(List<Book> BookList) throws ParseException {
		
		List<BookResponseDTO> bookListDTO = new ArrayList<BookResponseDTO>();
		
		for (Book book : BookList) {
			BookResponseDTO bookDTOfilled= new BookResponseDTO();
			
			bookDTOfilled= mapBookToDTO(book, bookDTOfilled);
			
			bookListDTO.add(bookDTOfilled);
			}
		return bookListDTO;
		
	}

	
	
	

	

}
