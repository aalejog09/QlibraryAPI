package com.api.qlibrary.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase persistente para identificar la entidad <Strong>Categoria</Strong> dentro de la aplicación..
 * @author LPinto
 *
 */

@Entity
@Table(name = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

			
	/**
	 * Variable de identificación única generada por sequencia de base de datos.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="category_id_seq")
	@SequenceGenerator(name = "category_id_seq", sequenceName="category_id_seq", allocationSize = 1)
	@Column(name = "id")
	private Integer id;
	
	
	/**
	 * Variable que identifica el nombre de la categoria del libro.
	 */
	@Column(name = "name")
	private String name;
	

}
