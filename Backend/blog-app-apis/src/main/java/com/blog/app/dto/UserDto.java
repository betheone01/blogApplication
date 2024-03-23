package com.blog.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {
	
	
	
	private Integer  id;
	@NotEmpty
	@Size(min=4,message="Username should be min of 4 characters ")
	private String name;
	@Email(message="email address is not valid")
	
	private String email;
	@NotEmpty
	@Size(min=8 ,message="Password should be min of 8 characters ")
	private String password;
	@NotEmpty
	private String about;
	
 
}
