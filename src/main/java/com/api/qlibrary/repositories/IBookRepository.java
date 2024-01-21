package com.api.qlibrary.repositories;

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


}
