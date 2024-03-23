package com.blog.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
	
	
	private Integer categoryId;
	
	@NotBlank
	@Size(min = 4,message = "CategoryTitle should be of min 4 chars")
	private String categoryTitle;
	@NotBlank
	@Size(min = 4,message = "Category Description should be of min 4 chars")
	private String categoryDescription;

}
