package com.blog.app.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.dto.CategoryDto;
import com.blog.app.entities.Category;
import com.blog.app.exceptions.ResourceNotFoundException;
import com.blog.app.repositories.CategoryRepo;
import com.blog.app.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category cat=this.modelMapper.map(categoryDto, Category.class);
		
		
		return this.modelMapper.map(this.categoryRepo.save(cat), CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		// TODO Auto-generated method stub
		
		Category cat= this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
	
		Category updatedCat=this.categoryRepo.save(cat);
		
		return this.modelMapper.map(updatedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		System.out.println("In deleteCategory Service IMpl");
		Category cat= this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		Category deletedCat=cat;
		this.categoryRepo.delete(cat);
		
		return this.modelMapper.map(deletedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat= this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		// TODO Auto-generated method stub
		
		List<Category>categories=this.categoryRepo.findAll();
		
		List<CategoryDto>categoryDtos=categories.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		
		return categoryDtos;
	}
	

}
