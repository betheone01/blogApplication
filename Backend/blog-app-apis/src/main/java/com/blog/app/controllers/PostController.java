package com.blog.app.controllers;

import java.util.*;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.blog.app.dto.PostDto;
import com.blog.app.entities.Post;
import com.blog.app.exceptions.ResourceNotFoundException;
import com.blog.app.payloads.PostResponse;
import com.blog.app.services.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {
	 
	
	@Autowired
	private PostService postService;
	

	@PostMapping("/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDto>createPost(@RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId)
	{
		PostDto postDto1=this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(postDto1,HttpStatus.CREATED); 
		
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<PostResponse> getPostByUserId(
			@PathVariable Integer userId,
			@RequestParam (value="pageNumber",defaultValue="0",required=false)Integer pageNumber,
			@RequestParam (value="pageSize",defaultValue="1",required=false)Integer pageSize
			)
	{
		PostResponse postresp=this.postService.getPostByUser(userId,pageNumber,pageSize);
		return new ResponseEntity<PostResponse>(postresp, HttpStatus.OK);
	}

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<PostResponse>getPostByCategoryId(@PathVariable Integer categoryId,
			@RequestParam (value="pageNumber",defaultValue="0",required=false)Integer pageNumber,
			@RequestParam (value="pageSize",defaultValue="1",required=false)Integer pageSize)
	{
		PostResponse postresp=this.postService.getPostByCategory(categoryId,pageNumber,pageSize);
		return new ResponseEntity<PostResponse>( postresp, HttpStatus.OK);
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto>getPostById(@PathVariable Integer postId)
	{
		PostDto postDto=this.postService.getPostByPostId(postId);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<PostResponse>getAllPosts(
			@RequestParam (value="pageNumber",defaultValue="0",required=false)Integer pageNumber,
			@RequestParam (value="pageSize",defaultValue="1",required=false)Integer pageSize
			)
	{
		PostResponse postResponse=this.postService.getAllPosts(pageNumber,pageSize);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
	@DeleteMapping("/{postId}")
	public ResponseEntity<PostDto> deletePostByPostId(@PathVariable Integer postId)
	{
	    try {
	        System.out.println("In delete Controller");
	        PostDto deletedPost = this.postService.deletePostByPostId(postId);
	        return new ResponseEntity<>(deletedPost, HttpStatus.OK);
	    } catch (ResourceNotFoundException e) {
	        // Log the exception
//	        logger.error("Resource not found for postId: {}", postId, e);
	        // Return an error response with appropriate status code
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    } catch (Exception e) {
	        // Log any other unexpected exceptions
//	        logger.error("Unexpected error occurred while deleting post", e);
	        // Return an error response with 500 status code
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	

	
	@PutMapping("/{postId}")
	public ResponseEntity<PostDto>updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId)
	{
		PostDto postdto=this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(postdto,HttpStatus.OK);
	}
	
}



























