package com.api.qlibrary.services.interfaces;

import java.util.Set;

import com.api.qlibrary.models.Category;

public interface ICategoryService {

	public Set<Category> findAllById(String categoryName);
	
	

}
