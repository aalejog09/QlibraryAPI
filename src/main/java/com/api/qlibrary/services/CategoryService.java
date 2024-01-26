package com.api.qlibrary.services;

import java.util.List;
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
	
	

/***
 * Metodo que implementa la consulta de todas las categorias por su nombre.
 */
	@Override
	public Set<Category> findAllById(String categoryName) throws Exception {

		Set<Category> category=iCategoryRepository.findAllByName(categoryName);
		log.debug("category: {}",category);
		
		if(category.isEmpty()==true) {
			throw new Exception("No se encontro la categoria solicitada.");
		}
		
		return category;
	}

	/**
	 * Metodo que implementa la busqueda de todas las categorias.
	 */
	@Override
	public List<Category> findAll() {
		List<Category> categoryList=iCategoryRepository.findAll();
		return categoryList;
	}
	
	@Override
	public Long getTotalCategories() {
		Long totalCategories=iCategoryRepository.count();
		return totalCategories;
	}
	
}
