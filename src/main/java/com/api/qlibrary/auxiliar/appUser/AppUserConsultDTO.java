package com.api.qlibrary.auxiliar.appUser;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;

import com.api.qlibrary.models.Role;

import lombok.Data;

@Data
public class AppUserConsultDTO {
	
	public AppUserConsultDTO() {
		super();
	}

	public AppUserConsultDTO(String username, Date creationDate, Date lastEntryDate, String status,
			String firstname, String lastname, String email, String dni, String phoneNumber, String financialEntity,
			Set<Role> roles) {
		super();
		this.username = username;
		this.creationDate = creationDate;
		this.lastEntryDate = lastEntryDate;
		this.status = status;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.dni = dni;
		this.phoneNumber = phoneNumber;
		this.financialEntity = financialEntity;
		this.roles = roles;
	}

	/**
	 * Variable que identifica el Nombre con el que el usuario inicia sesion en la app.
	 */
	private String username;
	
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
	 * Variable que identifica el estado del usuario.
	 * 
	 */
	@Column(name = "status")
	private  String status;
	
	/**
	 * Variable que identifica el nombre completo del usuario.
	 * 
	 */
	private  String firstname;
	
	/**
	 * Variable que identifica los apellidos del usuario.
	 * 
	 */
	private  String lastname;
	
	/**
	 * Variable que identifica el Correo Electronico del usuario.
	 * 
	 */
	private  String email;
	
	/**
	 * Variable que identifica el documento de identidad del usuario.
	 * 
	 */
	private  String dni;
	
	/**
	 * Variable que identifica el Nro. de contacto del usuario.
	 * 
	 */
	private  String phoneNumber;
	
	/**
	 * Variable que identifica la entidad financiera a la que se relaciona el usuario
	 * 
	 */
	private String financialEntity;
	
	/**
	 * Variable de Roles que puede tener el usuario.
	 */
	private Set<Role> roles = new HashSet<Role>();
	
}
