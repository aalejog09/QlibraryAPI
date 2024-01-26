package com.api.qlibrary.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.qlibrary.models.Author;

/**
 * Interfaz para el uso de los métodos de acceso a los datos por JpaRepository de la clase Author
 * @author Aalejo
 *
 */
@Repository
public interface IAuthorRepository extends JpaRepository< Author , Integer > {
	
	/**
	 * Método implementado para ubicar un Autor usando como parametro su identificador.
	 * @param id
	 * @return Author
	 */
	public Optional<Author> findById(Integer id);
	
	/**
	 * Método implementado para ubicar un Autor usando como parametro su codigo.
	 * @param code
	 * @return Author
	 */
	public Author findByCode(String code);
	
	/***
	 * Metodo implementado para ubicar una lista de autores por su codigo.
	 * @param code
	 * @return Author List 
	 */
	public Set<Author> findAllByCode(String code);

	/***
	 * Metodo implementado para ubicar la informacion de un autor por su codigo.
	 * @param authorCode
	 * @return Author
	 */
	public Author getAuthorDataByCode(String authorCode);
	
	/***
	 * Metodo implementado para ubicar un autor por la combinacion de nombre apellido y pais.
	 * @param name
	 * @param lastname
	 * @param country
	 * @return Author.
	 */
	public Author findByNameAndLastnameAndCountry(String name, String lastname, String country);
	
}
