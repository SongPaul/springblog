package com.brightyoun.blog.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brightyoun.blog.domain.model.entity.Post;

public interface PostDao extends JpaRepository<Post, Integer>{

}
