package com.api.qlibrary.models;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 
 * Clase persistente para identificar la entidad <Strong>Autor</Strong>  dentro de la aplicación.
 *
 * @author AAlejo
 */

@Entity
@Table(name = "author")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author{
		

	/**
	 * Variable de identificación única generada por sequencia de base de datos.
	 */
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
							   generator="author_id_seq")
	@SequenceGenerator(name = "author_id_seq", 
					   sequenceName="author_id_seq", 
					   allocationSize = 1)
	@Column(name = "id")
	private Integer id;
	
	/**
	 * Variable que identifica el Nombre del autor.
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * Variable que identifica los apellidos del autor.
	 * 
	 */
	@Column(name = "lastname")
	private  String lastname;
	
	
	/**
	 * Variable que identifica el pais de origen del autor.
	 * 
	 */
	@Column(name = "origin_country")
	private  String country;
	
	
	/**
	 * Variable que identifica la fecha de nacimiento del autor.
	 */
	@Column(name = "birthday")
	private  Date birthday;
	
	/**
	 * Variable que identifica el codigo del autor en la biblioteca.
	 * 
	 */
	@Column(name = "code")
	private  String code;
	
	/**
	 * Relacion establecida con el usuario que crea al autor.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="appuser_id")
	private Appuser appuser;
	
	
	/**
	 * Variable que identifica la fecha de creacion del autor.
	 */
	@Column(name = "creation_date")
	private  Date creationDate;
	
	
	
}
