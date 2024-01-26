package com.api.qlibrary.auxiliar.book;

import java.util.List;

import com.api.qlibrary.auxiliar.author.AuthorDTO;
import com.api.qlibrary.auxiliar.category.CategoryDTO;

import lombok.Data;


/***
 * 
 * Clase auxiliar para representar atributos publicos de un libro.
 * 
 * @author AAlejo
 *
 */
@Data
public class BookDTO {
	
	String title;
	String code;
	String publisher;
	String publicationDate;
	List<AuthorDTO> authors;
	List<CategoryDTO> categories;
	String authorCode;
	String categoryName;
	

}
