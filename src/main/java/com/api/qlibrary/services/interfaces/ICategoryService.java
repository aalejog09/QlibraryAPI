package com.api.qlibrary.services.interfaces;

import java.util.List;
import java.util.Set;

import com.api.qlibrary.models.Category;

/***
 * Interface que implementa los servicios de Categoria.
 * @author AAlejo
 *
 */
public interface ICategoryService {

	/**
	 * Metodo que implementa la busqueda de todas las categorias por su id.
	 * @param categoryName
	 * @return Category
	 * @throws Exception
	 */
	public Set<Category> findAllById(String categoryName) throws Exception;
	
	/**
	 * Metodo que implementa la busqueda de todas las categorias.
	 * @return Category list
	 * @throws Exception
	 */
	public List <Category> findAll();

	/** 
	 * Metodo que implementa la consulta del total de categorias registradas. 
	 * @return
	 */
	public Long getTotalCategories();

}
