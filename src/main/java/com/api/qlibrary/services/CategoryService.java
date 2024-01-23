package com.api.qlibrary.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.qlibrary.models.Category;
import com.api.qlibrary.repositories.ICategoryRepository;
import com.api.qlibrary.services.interfaces.ICategoryService;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase de métodos de servicios de categorias de libros.
 * @author Aalejo
 *
 */
@Slf4j
@Service
public class CategoryService implements ICategoryService {
	
	
	
	@Autowired
	private ICategoryRepository iCategoryRepository;
	
	


	@Override
	public Set<Category> findAllById(String categoryName) {

		Set<Category> category=iCategoryRepository.findAllByName(categoryName);
		log.info("category: {}",category);
		
		return category;
	}
	
}
