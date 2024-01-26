package com.api.qlibrary.auxiliar.book;

import lombok.Data;


/***
 * 
 * Clase auxiliar para representar atributos publicos.
 * 
 * @author AAlejo
 *
 */
@Data
public class BookByAuthorDTO {
	
	String authorName;
	String authorCode;
	Long totalBooks;

}
