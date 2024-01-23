package com.api.qlibrary.auxiliar.book;



import lombok.Data;

@Data
public class CreateBookDTO {
	
	String title;
	String code;
	String publisher;
	String publicationDate;
	String authorCode;
	String categoryName;
	

}
