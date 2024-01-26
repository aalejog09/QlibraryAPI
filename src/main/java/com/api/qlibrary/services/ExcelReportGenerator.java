package com.api.qlibrary.services;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.qlibrary.auxiliar.book.BookByCategoryDTO;
import com.api.qlibrary.services.interfaces.IAuthorService;
import com.api.qlibrary.services.interfaces.IBookService;
import com.api.qlibrary.services.interfaces.ICategoryService;
import com.api.qlibrary.util.Utility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ExcelReportGenerator {

	
	@Autowired
	private IAuthorService iAuthorService;
	
	@Autowired
	private ICategoryService iCategoryService;
	
	@Autowired
	private IBookService iBookService;
	
	@Autowired
	private Utility utility;
	
	
	public byte[] generateReport() throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Report"+utility.generatedReportCreation());

        
        CellStyle centeredStyle = workbook.createCellStyle();
        centeredStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        
        // Create header row
       
        Row headerRow = sheet.createRow(1);
        headerRow.createCell(0).setCellValue("Total de libros".toUpperCase());
        headerRow.createCell(1).setCellValue("Total de autores".toUpperCase());
        headerRow.createCell(2).setCellValue("Total de categorías".toUpperCase());
       
        List<BookByCategoryDTO> bookList=getBooksPerCategory();//lista de libros por categoria
       
        int i=3;
        
        for (BookByCategoryDTO bookCategoryList : bookList){
        	log.debug("valor entrante de i: {}",i);
        	headerRow.createCell(i).setCellValue(bookCategoryList.getCategoryName());
        	headerRow.setRowStyle(centeredStyle);
        	i++;
        }
        headerRow.setRowStyle(centeredStyle);

        // Create data rows
        Row dataRow = sheet.createRow(2);
        dataRow.setRowStyle(centeredStyle);
        dataRow.createCell(0).setCellValue(getTotalBooks());//total libros
        dataRow.createCell(1).setCellValue(getTotalAuthors());//total autores
        dataRow.createCell(2).setCellValue(getTotalCategories());//total categorias
        
        int k=3;
        for (BookByCategoryDTO bookCategoryList : bookList){
        	log.debug("valor entrante de k: {}",k);
        	dataRow.createCell(k).setCellValue(bookCategoryList.getTotalBooks());
        	// Auto-size columns
            for (int j = 0; j < k+3; j++) {
                sheet.autoSizeColumn(j);
            }
            k++;
        }
        dataRow.setRowStyle(centeredStyle);
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        byte[] data = bos.toByteArray();

        // Close workbook and stream
        bos.close();
        workbook.close();

        return data;
        
    }

    
    
    private Long getTotalAuthors() {
    	
    	Long totalAuthors= iAuthorService.getTotalAuthors();
    	log.debug("totalAuthors in report: {}",totalAuthors);
    	return totalAuthors;
    }

    private Long getTotalCategories() {
    	Long totalCategories= iCategoryService.getTotalCategories();
    	log.debug("totalCategories in report: {}",totalCategories);
    	return totalCategories;
    }

    private List<BookByCategoryDTO> getBooksPerCategory() {
    	List<BookByCategoryDTO> bookList = iBookService.countByCategory();
    	log.debug("bookList in report: {} ",bookList);
    	
    	return bookList;
    }
    
    private Long getTotalBooks() {
    	Long totalBooks = iBookService.getTotalBooks();
    	
    	log.debug("totalBooks in report: {}",totalBooks);
        return totalBooks;
    }

}
