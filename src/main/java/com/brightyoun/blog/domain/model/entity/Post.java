package com.brightyoun.blog.domain.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Post {
	@Id
	@GeneratedValue
	int id;
	
	String userId;
	String name;
	String _csrf;
	
	@NotNull
	@Size(min = 1, max = 255)
	@Column(nullable = false)
	String title;
	
	@Size(max = 255)
	String subtitle;
	
	@NotNull
	@Size(min = 1, max = 1000000000)
	@Column(length = 1000000000, nullable = false)
	String content;
	
	@Column(nullable = false)
	String regDate;
	
}
