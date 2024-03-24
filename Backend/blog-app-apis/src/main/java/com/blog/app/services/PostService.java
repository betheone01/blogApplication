package com.blog.app.services;

import java.util.List;

import com.blog.app.dto.PostDto;
import com.blog.app.entities.Post;
import com.blog.app.payloads.*;
public interface PostService {

	
	//create
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	
	//update
	PostDto updatePost(PostDto postDto ,Integer postId);
	
	
	// delete
	 PostDto deletePostByPostId(Integer postId);
	 
	 //get 
	 PostDto getPostByPostId(Integer postId);
	 
	 //getAll
	 
	 PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	 
	 //get all post by user
	 PostResponse getPostByUser(Integer userId,Integer pageNumber,Integer pageSize);
	 
	 
	 
	 //get all post by category
	PostResponse getPostByCategory(Integer categoryId,Integer pageNumber,Integer pageSize);
	 
	 //serach post by keyword
	 List<PostDto>searchPost(String keyword);
	 
}
