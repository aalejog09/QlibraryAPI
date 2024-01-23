package com.api.qlibrary.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 
 * Clase persistente para identificar la entidad <Strong>Libro</Strong> dentro de la aplicación.
 *
 * @author AAlejo
 */

@Entity
@Table(name = "book")
@Data@AllArgsConstructor
@NoArgsConstructor
public class Book{
		

	/**
	 * Variable de identificación única generada por sequencia de base de datos.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
							   generator="book_id_seq")
	@SequenceGenerator(name = "book_id_seq", 
					   sequenceName="book_id_seq", 
					   allocationSize = 1)
	@Column(name = "id")
	private Integer id;
	
	/**
	 * Variable que identifica el titulo del libro.
	 */
	@Column(name = "title")
	private String title;
	
	/**
	 * Variable que identifica la editorial del libro.
	 */
	@Column(name = "publisher")
	private  String publisher;
	
	/**
	 * Variable que identifica el codigo publico del libro en la biblioteca.
	 * 
	 */
	@Column(name = "code")
	private  String code;
	

	/**
	 * Relacion establecida con el usuario que crea el libro.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="appuser_id")
	private Appuser appuser;
	
	
	/**
	 * Variable que identifica el momento de creacion del libro.
	 */
	@Column(name = "creation_date")
	private  Date creationDate;
	
	/**
	 * Variable que identifica la fecha de publicacion del libro.
	 */
	@Column(name = "publication_date")
	private  Date publicationDate;
	
	/**
	 * Variable que permite relacionar un libro con uno o varios autores
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="book_author_aux",
	joinColumns=@JoinColumn(name="book_id"),
	inverseJoinColumns=@JoinColumn(name="author_id"))
	private Set<Author> authors = new HashSet<Author>();
	
	/**
	 * Variable que permite relacionar un libro con una o varias categorias
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="book_category_aux",
	joinColumns=@JoinColumn(name="book_id"),
	inverseJoinColumns=@JoinColumn(name="category_id"))
	private Set<Category> categories = new HashSet<Category>();


	

}
