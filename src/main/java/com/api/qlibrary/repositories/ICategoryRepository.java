package com.api.qlibrary.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.qlibrary.models.Category;


/**
 * Interfaz para el uso de los métodos de acceso a los datos por JpaRepository de la clase Category
 * @author Aalejo
 *
 */
@Repository
public interface ICategoryRepository extends JpaRepository< Category , Integer > {
	
	/**
	 * Método implementado para ubicar una categoria usando como parametro su identificador.
	 * @param id
	 * @return Category
	 */
	public Optional<Category> findById(Integer id);
	
	/**
	 * Método implementado para ubicar un Autor usando como parametro su codigo.
	 * @param code
	 * @return Author
	 */
	public Category findByName(String categoryName);
	
	/**
	 * Metodo implementado para ubicar todas las categorias por su nombre.
	 * @param categoryName
	 * @return Set<Category>
	 */
	public Set<Category> findAllByName(String categoryName);

	
	/***
	 * Metodo implementado para ubicar la data de la categoria por su nombre.
	 * @param categoryName
	 * @return Category
	 */
	public Category getCategoryDataByName(String categoryName);
	
	
}
