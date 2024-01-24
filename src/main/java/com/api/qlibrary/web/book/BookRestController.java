package com.api.qlibrary.web.book;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.qlibrary.auxiliar.book.AuthorBookDTO;
import com.api.qlibrary.auxiliar.book.BookConsultDTO;
import com.api.qlibrary.auxiliar.book.BookResponseDTO;
import com.api.qlibrary.auxiliar.book.CategoryBookDTO;
import com.api.qlibrary.auxiliar.book.CreateBookDTO;
import com.api.qlibrary.services.interfaces.IBookService;

import lombok.extern.slf4j.Slf4j;


/**
 * @author AAlejo
 * 
 * Controlador principal de acceso a la aplicacion. 
 * */
@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/qlibray/api/v1/book")
public class BookRestController {

	/**
	 * Variable que se usa para importar métodos del servicio de usuarios.
	 */
	@Autowired
	IBookService bookServiceinterface;
	
	
	/**
	 * Método de controlador que llama servicio para crear un libro.
	 * @param BookDTO
	 * 
	 * @return bookDTO
	 * @throws Exception
	 */
	@PostMapping(value="/create")
	public ResponseEntity<?> createBook(@RequestBody  @Valid CreateBookDTO bookDTO) throws Exception {
		log.info("data recibida: {}",bookDTO.toString());
		Map<String,Object> response = new HashMap<String,Object>();
		try {
			CreateBookDTO createdbook = bookServiceinterface.createBook(bookDTO);
			log.info("createdbook: {}",createdbook);
			
			response.put("createdbook", createdbook);
			response.put("Status","Creado exitosamente");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("status", "Error");
			response.put("message", "Ocurrio un error al registrar al libro  por favor verifique los datos e intentelo nuevamente");
			response.put("exception", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE);  //throw new ObjectNotFoundException(null, null); 
		}
	}
	
	
	/**
	 * Método de controlador que llama servicio que consulta todos los libros registrados.
	 * @param AuthorDTO
	 * 
	 * @return Author
	 * @throws Exception
	 */
	@GetMapping(value="/consult/listAll")
	public ResponseEntity<?> getAllBooks() throws Exception {
		log.info("Consultando los libros registrados");
		Map<String,Object> response = new HashMap<String,Object>();
		try {
			List<BookResponseDTO> bookList = bookServiceinterface.getAllBookList();
			log.info("bookList: {}",bookList);
			response.put("bookList", bookList);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);  //throw new ObjectNotFoundException(null, null); 
		}
	}
	
	
	/**
	 * Método de controlador que llama servicio que agrega una categoria a un libro.
	 * @return CategoryBookDTO
	 * @throws Exception
	 */
	@PostMapping(value= "/addCategory")
	public ResponseEntity<?> addCategoryToBook(@RequestBody CategoryBookDTO data) throws Exception {		
		
		Map<String,Object> response = new HashMap<String,Object>();
		try {
			BookResponseDTO bookResponse=bookServiceinterface.addCategoryToBook(data);
			log.info("bookResponse: {}",bookResponse);
			
			response.put("bookResponse", bookResponse);
			response.put("Status","Agregado exitosamente");
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("status", "Error");
			response.put("message", "Ocurrio un error al agregar la categoria al libro  por favor verifique los datos e intentelo nuevamente");
			response.put("exception", e.getMessage());
			return new ResponseEntity<>( response,HttpStatus.NOT_FOUND);
		}

	}
	
	
	/**
	 * Método de controlador que llama servicio que agrega una categoria a un libro.
	 * @return FinancialEntity
	 * @throws Exception
	 */
	@PostMapping(value= "/addAuthor")
	public ResponseEntity<?> addAuthorToBook(@RequestBody AuthorBookDTO data) throws Exception {		
		
		Map<String,Object> response = new HashMap<String,Object>();
		try {
			BookResponseDTO bookResponse=bookServiceinterface.addAuthorToBook(data);
			log.info("bookResponse: {}",bookResponse);
			
			response.put("bookResponse", bookResponse);
			response.put("Status","Agregado exitosamente");
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("status", "Error");
			response.put("message", "Ocurrio un error al agregar el author al libro  por favor verifique los datos e intentelo nuevamente");
			response.put("exception", e.getMessage());
			return new ResponseEntity<>( response,HttpStatus.NOT_FOUND);
		}

	}
	
	/**
	 * Método de controlador que llama servicio que consultar un libro por su codigo.
	 * @param BookConsultDTO
	 * 
	 * @return Author
	 * @throws Exception
	 */
	@PostMapping(value="/consult/getInfo")
	public ResponseEntity<?> getBookInfo(@RequestBody BookConsultDTO bookDTO) throws Exception {
		log.info("Consultando la informacion del libro con el codigo: {}",bookDTO);
		Map<String,Object> response = new HashMap<String,Object>();
		try {
			BookResponseDTO book = bookServiceinterface.getBookInfoByCode(bookDTO);
			log.info("book: {}",book);
			response.put("book", book);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("status", "Error");
			response.put("message", "Ocurrio un error al consultar el libro.");
			response.put("exception", e.getMessage());
			return new ResponseEntity<>( response,HttpStatus.NOT_FOUND);
		}
	}
	
	
	/**
	 * Método de controlador que llama servicio que consultar un libro por su codigo.
	 * @param BookConsultDTO
	 * 
	 * @return Author
	 * @throws Exception
	 */
	@PostMapping(value="/consult/listByCategoryId")
	public ResponseEntity<?> getAllByCategoryId(@RequestBody BookConsultDTO bookDTO) throws Exception {
		log.info("Consultando la informacion del libro con el categoryId: {}",bookDTO);
		Map<String,Object> response = new HashMap<String,Object>();
		try {
			List<BookResponseDTO> book = bookServiceinterface.getBookInfoByCategory(bookDTO);
			log.info("book: {}",book);
			response.put("book", book);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("status", "Error");
			response.put("message", "Ocurrio un error al consultar el libro.");
			response.put("exception", e.getMessage());
			return new ResponseEntity<>( response,HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
    
}
