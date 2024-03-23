package com.blog.app.entities;

import java.util.Date;

import org.hibernate.annotations.ManyToAny;
import org.springframework.stereotype.Service;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
//	@Column(nullable = false)
	private String title;
	
	@Column(length = 1000)
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	
	@ManyToOne
	@JoinColumn(name="categoryId")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	
	
	
	
	

}
