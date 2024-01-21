package com.api.qlibrary.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.qlibrary.models.Author;

/**
 * Interfaz para el uso de los métodos de acceso a los datos por JPA/HIBERNATE de la clase Author
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

	public Author getAuthorDataByCode(String authorCode);
	
	public Author findByNameAndLastnameAndCountry(String name, String lastname, String country);
	
}
