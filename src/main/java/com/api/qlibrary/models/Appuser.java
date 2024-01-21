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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * 
 * Clase persistente para identificar la entidad <Strong>Usuario</Strong>  dentro de la aplicación.
 *
 * @author AAlejo
 */

@Entity
@Table(name = "appuser")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appuser{
		

	/**
	 * Variable de identificación única generada por sequencia de base de datos.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
							   generator="appuser_id_seq")
	@SequenceGenerator(name = "appuser_id_seq", 
					   sequenceName="appuser_id_seq", 
					   allocationSize = 1)
	@Column(name = "id")
	private Integer id;
	
	/**
	 * Variable que identifica el Nombre con el que el usuario inicia sesion en la app.
	 */
	@Column(name = "name")
	private String username;
	
	/**
	 * Variable que identifica la clave con la que el usuario inicia sesion en la app.
	 */
	@Column(name = "access_code")
	private  String accessCode;
	
	/**
	 * Variable que identifica la fecha de creacion del usuario.
	 */
	@Column(name = "creation_date")
	private  Date creationDate;
	
	/**
	 * Variable que identifica la ultima conexion del usuario.
	 */
	@Column(name = "last_entry_date")
	private  Date lastEntryDate;
	
	/**
	 * Variable que identifica el nombre completo del usuario.
	 * 
	 */
	@Column(name = "firstname")
	private  String firstname;
	
	/**
	 * Variable que identifica los apellidos del usuario.
	 * 
	 */
	@Column(name = "lastname")
	private  String lastname;
	
	/**
	 * Variable que identifica el Correo Electronico del usuario.
	 * 
	 */
	@Column(name = "email")
	private  String email;
	
	/**
	 * Variable que identifica el documento de identidad del usuario.
	 * 
	 */
	@Column(name = "identification_code")
	private  String identificationCode;
	
	/**
	 * Variable que permite relacionar un usuario con una o varias roles
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="appuser_role_aux",
	joinColumns=@JoinColumn(name="appuser_id"),
	inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<Role> roles = new HashSet<Role>();
	
	
}
