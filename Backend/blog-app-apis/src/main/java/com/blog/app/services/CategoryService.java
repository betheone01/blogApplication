package com.blog.app.services;

import java.util.List;

import com.blog.app.dto.CategoryDto;


public interface CategoryService {
	//create
		public CategoryDto createCategory(CategoryDto categoryDto);
		
		//update
		public CategoryDto updateCategory (CategoryDto categoryDto ,Integer categoryId);
		
		//delete
		
		public CategoryDto deleteCategory(Integer categoryId);
		
		//get
		public CategoryDto getCategoryById(Integer categoryId);
		
		//getAll

		public List<CategoryDto>getAllCategory();

}
