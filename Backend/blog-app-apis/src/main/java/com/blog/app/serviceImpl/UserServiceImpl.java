package com.blog.app.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.dto.UserDto;
import com.blog.app.entities.User;
import com.blog.app.repositories.UserRepo;
import com.blog.app.services.UserService;
import com.blog.app.exceptions.*;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	

	@Override
	public UserDto createUser(UserDto userDto) {
		
		
		User user=this.dtoToUser(userDto);
		User savedUser=this.userRepo.save(user);
		
		
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) 
	{
		// TODO Auto-generated method stub
		User user =this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","userId",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		
		User updatedUser=this.userRepo.save(user);
		
		
		
		return this.userToDto(updatedUser);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<User>users=this.userRepo.findAll();
		List<UserDto>userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		
		return userDtos;
	}

	@Override
	public UserDto deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		User user =this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","userId",userId));
		this.userRepo.delete(user);
		
		
		return this.userToDto(user);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user =this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","userId",userId));
		
		
		return this.userToDto(user);
	}
	
	public UserDto userToDto(User user)
	{
		
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
//		userDto.setAbout(user.getAbout());
//		userDto.setEmail(user.getEmail());
////		userDto.setAbout(user.getAbout());
//		userDto.setId(user.getId());
//		userDto.setPassword(user.getPassword());
//		userDto.setName(user.getName());
		
		
		return userDto;
		
		
	}
	public User dtoToUser(UserDto userDto)
	{
		User user =this.modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		
		
		return user;
	}

}
