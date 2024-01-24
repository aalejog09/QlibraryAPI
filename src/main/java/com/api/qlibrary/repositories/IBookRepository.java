package com.api.qlibrary.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.qlibrary.models.Book;

/**
 * Interfaz para el uso de los métodos de acceso a los datos por JPA/HIBERNATE de la clase Book
 * @author AAlejo
 *
 */
@Repository
public interface IBookRepository extends JpaRepository< Book, Integer >{

	public List<Book> findAllByOrderByCreationDateDesc();
	
	public Book findByCode(String code);
	
	public List<Book> findAllByAuthors(String code);

	public List<Book> findAllByCategories(String id);
	
	public List<Book> findByCategoriesId(Integer categoryId);
}
