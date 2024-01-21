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
 * 
 * Clase persistente para identificar un rol dentro de la aplicación.
 *
 * @author AAlejo
 * 
 */

@Entity
@Table(name = "role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

	/**
	 * Variable de identificación única generada por sequencia de base de datos.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
							   generator="role_id_seq")
	@SequenceGenerator(name = "role_id_seq", 
					   sequenceName="role_id_seq", 
					   allocationSize = 1)
	@Column(name = "id")
	private int id;
	
	/**
	 * Nombre descriptivo del rol.
	 */
	@Column(name = "name")
	private String roleName;
	
	/**
	 * Descripcion adicional para el rol.
	 */
	@Column(name = "description")
	private String description;


}
