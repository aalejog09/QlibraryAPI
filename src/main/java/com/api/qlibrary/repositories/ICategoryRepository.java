package com.api.qlibrary.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.qlibrary.models.Category;


/**
 * Interfaz para el uso de los métodos de acceso a los datos por JPA/HIBERNATE de la clase Author
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
	
	public Set<Category> findAllByName(String categoryName);

	public Category getCategoryDataByName(String categoryName);
	
	
}
