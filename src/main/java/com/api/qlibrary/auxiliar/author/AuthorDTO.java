package com.api.qlibrary.auxiliar.author;


import lombok.Data;



/***
 * 
 * Clase auxiliar para representar atributos  publicos de un autor.
 * 
 * 
 * @author AAlejo
 *
 */
@Data
public class AuthorDTO {

	String name;
	String lastname;
	String birthday;
	String code;
	String country;

	
}
