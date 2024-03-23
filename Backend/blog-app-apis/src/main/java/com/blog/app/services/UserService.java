package com.blog.app.services;

import java.util.List;

import com.blog.app.dto.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto userDto);
	UserDto updateUser(UserDto user,Integer userId);
	List<UserDto>getAllUsers();
	
	UserDto deleteUser(Integer userId);
	UserDto getUserById(Integer userId);
	
	

}
