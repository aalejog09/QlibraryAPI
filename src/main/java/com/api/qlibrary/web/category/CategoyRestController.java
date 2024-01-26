package com.api.qlibrary.web.category;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.qlibrary.auxiliar.appUser.AppUserCreateDTO;
import com.api.qlibrary.auxiliar.appUser.AppuserDTO;
import com.api.qlibrary.auxiliar.book.BookByAuthorDTO;
import com.api.qlibrary.models.Category;
import com.api.qlibrary.services.interfaces.IAppuserService;
import com.api.qlibrary.services.interfaces.ICategoryService;

import lombok.extern.slf4j.Slf4j;


/**
 * @author AAlejo
 * 
 * Controlador de los servicios de usuario.
 * */
@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/qlibray/api/v1/category")
public class CategoyRestController {

	
	/**
	 * Variable que se usa para importar métodos del servicio de usuarios.
	 */
	@Autowired
	ICategoryService iCategoryService;
	
	
	/**
	 * Método de controlador que llama servicio que consultar todas las categorias.
	 * 
	 * @return Category List
	 * @throws Exception
	 */
	@GetMapping(value="/consult/listAll")
	public ResponseEntity<?> countTotalByAuthor() throws Exception {
		log.info("Consultando categorias");
		Map<String,Object> response = new HashMap<String,Object>();
		try {
			List<Category> totalCategories = iCategoryService.findAll();
			log.info("totalCategories: {}",totalCategories);
			response.put("categoryList", totalCategories);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("status", "Error");
			response.put("message", "Ocurrio un error al consultar las categorias.");
			response.put("exception", e.getMessage());
			return new ResponseEntity<>( response,HttpStatus.NOT_FOUND);
		}
	}
	

    


    
    
}
