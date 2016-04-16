package com.brightyoun.blog.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brightyoun.blog.domain.model.entity.Hello;

public interface HelloDao extends JpaRepository<Hello, Integer>{

}
