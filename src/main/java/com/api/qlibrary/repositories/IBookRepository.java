package com.api.qlibrary.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.qlibrary.models.Book;

/**
 * Interfaz para el uso de los métodos de acceso a los datos por JpaRepository de la clase Book
 * @author AAlejo
 *
 */
@Repository
public interface IBookRepository extends JpaRepository< Book, Integer >{

	/***
	 * Metodo implementado para encontrar una lista de libros ordenada por su fecha de creacion.
	 * @return lista de libros.
	 */
	public List<Book> findAllByOrderByCreationDateDesc();
	
	/***
	 * Metodo implementado para ubicar un libro por su codigo.
	 * @param code
	 * @return Book
	 */
	public Book findByCode(String code);
	
	/***
	 * Metodo implementado para todos los libros por author.
	 * @param code
	 * @return Book list
	 */
	public List<Book> findAllByAuthors(String code);

	
	/***
	 * Metodo implementado para todos los libros por categoria asociada.
	 * @param code
	 * @return Book list
	 */
	public List<Book> findAllByCategories(String id);
	
	/***
	 * Metodo implementado para todos los libros por categoria asociada.
	 * @param code
	 * @return Book list
	 */
	public List<Book> findByCategoriesId(Integer categoryId);

	
	/***
	 * Metodo implementado para todos los libros por autores asociados.
	 * @param code
	 * @return Book list
	 */
	public List<Book> findByAuthorsId(Integer authorsId);
	
	/***
	 * Metodo implementado para obtener la cantidad de libros por categoria.
	 * @param categoryName
	 * @return Cantidad libros.
	 */
	@Query("SELECT COUNT(b) FROM Book b JOIN b.categories c WHERE c.name = :categoryName")
	public Long countByCategoryName(@Param("categoryName") String categoryName);
	
	
	/***
	 * Metodo implementado para obtener la cantidad de libros por autores.
	 * @param categoryName
	 * @return Cantidad libros.
	 */
	@Query("SELECT COUNT(b) FROM Book b JOIN b.authors a WHERE a.code = :authorCode")
	public Long countByAuthorCode(@Param("authorCode") String authorCode);
	
}
