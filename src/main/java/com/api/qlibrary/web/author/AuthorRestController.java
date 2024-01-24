package com.api.qlibrary.web.author;


import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.qlibrary.auxiliar.author.AuthorDTO;
import com.api.qlibrary.services.interfaces.IAuthorService;

import lombok.extern.slf4j.Slf4j;


/**
 * @author AAlejo
 * 
 * Controlador principal de acceso a la aplicacion. 
 * */
@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/qlibray/api/v1/author")
public class AuthorRestController {

	/**
	 * Variable que se usa para importar métodos del servicio de usuarios.
	 */
	@Autowired
	IAuthorService authorServiceInterface;
	

	
	/**
	 * Método de controlador que llama servicio que consulta al author por su codigo.
	 * @param AuthorDTO
	 * 
	 * @return Author
	 * @throws Exception
	 */
	@PostMapping(value="/consult/getInfo")
	public ResponseEntity<?> getAuthorInfo(@RequestBody @Valid AuthorDTO authorDTO) throws Exception {
		log.info("data recibida: {}",authorDTO.toString());
		try {
			AuthorDTO foundAuthor = authorServiceInterface.getAuthorDataByAuthorCode(authorDTO.getCode());
			log.info("foundAuthor: {}",foundAuthor);
			return new ResponseEntity<>(foundAuthor, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);  //throw new ObjectNotFoundException(null, null); 
		}
	}
	
	/**
	 * Método de controlador que llama servicio que consulta al author por su codigo.
	 * @param AuthorDTO
	 * 
	 * @return Author
	 * @throws Exception
	 */
	@PostMapping(value="/create")
	public ResponseEntity<?> createAuthor(@RequestBody @Valid AuthorDTO authorDTO) throws Exception {
		log.info("data recibida: {}",authorDTO.toString());
		Map<String,Object> response = new HashMap<String,Object>();
		try {
			AuthorDTO createdAuthor = authorServiceInterface.createAuthor(authorDTO);
			log.info("createdAuthor: {}",createdAuthor);
			
			response.put("createdAuthor", createdAuthor);
			response.put("Status","Creado exitosamente");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("status", "Error");
			response.put("message", "Ocurrio un error al registrar al autor por favor verifique los datos e intentelo nuevamente");
			response.put("exception", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE);  //throw new ObjectNotFoundException(null, null); 
		}
	}
    
    
}
