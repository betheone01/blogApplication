package com.blog.app.controllers;

import java.util.List;
import java.util.Map;

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

import com.blog.app.dto.UserDto;
import com.blog.app.payloads.ApiResponse;
import com.blog.app.services.UserService;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userSevice;
	
	@PostMapping("/createUser")
	public ResponseEntity<UserDto>createUser(@Valid @RequestBody UserDto  userDto)
	{
		UserDto createdUser=this.userSevice.createUser(userDto);
		return new ResponseEntity<>(createdUser,HttpStatus.CREATED);		
	}
	
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<UserDto>updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId)
	{
		UserDto userDto1=this.userSevice.updateUser(userDto, userId);
		return new ResponseEntity<UserDto>(userDto1,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<UserDto>deleteUser(@PathVariable Integer userId)
	{
		UserDto userDto=this.userSevice.deleteUser(userId);
		//return new ResponseEntity(new ApiResponse("User deleted Succesfully",true)),HttpStatus.OK);
		return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
		
		
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDto>>getAllUsers()
	{
		List<UserDto>userDtos=this.userSevice.getAllUsers();
		return new ResponseEntity<List<UserDto>>(userDtos, HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto>getUserById(@PathVariable Integer userId)
	{
//		UserDto userdto=this.userSevice.getUserById(userId);
//		return new ResponseEntity<UserDto>(userdto, HttpStatus.OK);
		return new ResponseEntity<UserDto>(this.userSevice.getUserById(userId), HttpStatus.OK);
		
	}
	
}
