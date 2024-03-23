package com.blog.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.dto.CategoryDto;
import com.blog.app.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	
	//create
	@PostMapping("/createCategory")
	public ResponseEntity<CategoryDto>createCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		
		CategoryDto catDto=this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(catDto, HttpStatus.CREATED);
		
	}
	
	//update 
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto>updateCategory( @Valid @RequestBody CategoryDto categoryDto , @PathVariable Integer categoryId)
	{
		
		CategoryDto updatedCat=this.categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(updatedCat, HttpStatus.OK);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<CategoryDto>deleteCategory(@PathVariable Integer categoryId)
	{
		CategoryDto categoryDto=this.categoryService.deleteCategory(categoryId);
		System.out.println(categoryDto);
		return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
		
		//return new ResponseEntity(new ApiResponse("User deleted Succesfully",true)),HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>>getAllCategories()
	{
		List<CategoryDto>categories =this.categoryService.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(categories, HttpStatus.OK);
	}
	
	@GetMapping("{categoryId}")
	public ResponseEntity<CategoryDto>getCategoryById(@PathVariable Integer categoryId)
	{
		
		CategoryDto cat=this.categoryService.getCategoryById(categoryId);
		return new ResponseEntity<CategoryDto>(cat,HttpStatus.OK);
		
	}
	

}

