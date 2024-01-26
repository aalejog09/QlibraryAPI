package com.api.qlibrary.auxiliar.book;

import lombok.Data;


/***
 * 
 * Clase auxiliar para representar atributos publicos de un libro.
 * 
 * @author AAlejo
 *
 */
@Data
public class CreateBookDTO {
	
	String title;
	String code;
	String publisher;
	String publicationDate;
	String authorCode;
	String categoryName;
	

}
