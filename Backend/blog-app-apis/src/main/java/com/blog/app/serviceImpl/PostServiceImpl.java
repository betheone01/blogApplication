package com.blog.app.serviceImpl;

import java.util.Date;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.app.dto.PostDto;
import com.blog.app.entities.Category;
import com.blog.app.entities.Post;
import com.blog.app.entities.User;
import com.blog.app.exceptions.ResourceNotFoundException;
import com.blog.app.payloads.PostResponse;
import com.blog.app.repositories.CategoryRepo;
import com.blog.app.repositories.PostRepo;
import com.blog.app.repositories.UserRepo;
import com.blog.app.services.PostService;


@Service
public class PostServiceImpl  implements PostService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired 
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	@Override
	public PostDto createPost(PostDto postDto ,Integer userId,Integer categoryId) {
		// TODO Auto-generated method stub
		
		 User user = this.userRepo.findById(userId)
	                .orElseThrow(() -> new ResourceNotFoundException("User ", "User id", userId));

	        Category category = this.categoryRepo.findById(categoryId)
	                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id ", categoryId));

	        Post post = this.modelMapper.map(postDto, Post.class);
	        post.setImageName("default.png");
	        post.setAddedDate(new Date());
	        post.setUser(user);
	        post.setCategory(category);

	        Post newPost = this.postRepo.save(post);

	        return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post =this.postRepo.findById(postId).orElseThrow( ()-> new ResourceNotFoundException("Post", "postId", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		this.postRepo.save(post);
		
		
		
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto deletePostByPostId(Integer postId) {
		// TODO Auto-generated method stub
		System.out.println("In delete servcie impl ");
		Post post =this.postRepo.findById(postId).orElseThrow( ()-> new ResourceNotFoundException("Post", "postId", postId));
		this.postRepo.delete(post);
		return this.modelMapper.map(post, PostDto.class);
		

	
	}

	@Override
	public PostDto getPostByPostId(Integer postId) {
		// TODO Auto-generated method stub
		Post post =this.postRepo.findById(postId).orElseThrow( ()-> new ResourceNotFoundException("Post", "postId", postId));
		
		
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
	    // Create a Pageable object
		Sort sort =null;
		if(sortDir.equals("asc"))
		{
			sort=Sort.by(sortBy).ascending();
		}
		else
		{
			sort=Sort.by(sortBy).descending();
			
		}
	    Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
	    
	    // Fetch a page of posts using pagination
	    Page<Post> pagePost = this.postRepo.findAll(pageable);
	    
	    // Map the posts of the current page to DTOs
	    List<PostDto> postDtos = pagePost.getContent()
	            .stream()
	            .map(post -> this.modelMapper.map(post, PostDto.class))
	            .collect(Collectors.toList());
	    
	    // Create a PostResponse object and set its properties
	    PostResponse postResponse = new PostResponse();
	    postResponse.setContent(postDtos);
	    postResponse.setPageNumber(pagePost.getNumber());
	    postResponse.setPageSize(pagePost.getSize());
	    postResponse.setTotalElements(pagePost.getTotalElements());
	    postResponse.setTotalPages(pagePost.getTotalPages());
	    postResponse.setLastPage(pagePost.isLast());
	    
	    return postResponse;
	}

	@Override
	public PostResponse getPostByUser(Integer userId,Integer pageNumber,Integer pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable=PageRequest.of(pageNumber, pageSize);
		Page<Post>pagePost=this.postRepo.findAll(pageable);
		List<PostDto>postDtos=pagePost.getContent()
	            .stream()
	            .map(post -> this.modelMapper.map(post, PostDto.class))
	            .collect(Collectors.toList());
		
		User user =this.userRepo.findById(userId).orElseThrow( ()-> new ResourceNotFoundException("User", "userId", userId));
		PostResponse postResp=new PostResponse();
		postResp.setContent(postDtos);
		postResp.setPageNumber(pagePost.getNumber());
		postResp.setPageSize(pagePost.getSize());
		postResp.setTotalElements(pagePost.getTotalElements());
		postResp.setTotalPages(pagePost.getTotalPages());
		postResp.setLastPage(pagePost.isLast());
		
		
		
		return postResp;
	}

	@Override
	public PostResponse getPostByCategory(Integer categoryId,Integer pageNumber,Integer pageSize) {
		// TODO Auto-generated method stub
		Category category =this.categoryRepo.findById(categoryId).orElseThrow( ()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
		
		Pageable pageable=PageRequest.of(pageNumber, pageSize);
		Page<Post>pagePost=this.postRepo.findAll(pageable);
		List<PostDto>postDtos=pagePost.getContent()
	            .stream()
	            .map(post -> this.modelMapper.map(post, PostDto.class))
	            .collect(Collectors.toList());
		
		
		PostResponse postResp=new PostResponse();
		postResp.setContent(postDtos);
		postResp.setPageNumber(pagePost.getNumber());
		postResp.setPageSize(pagePost.getSize());
		postResp.setTotalElements(pagePost.getTotalElements());
		postResp.setTotalPages(pagePost.getTotalPages());
		postResp.setLastPage(pagePost.isLast());
		
		
		
		return postResp;

	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
