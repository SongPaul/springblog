package com.brightyoun.blog.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	
	@RequestMapping("/hello")
	public String index(Model model){
		System.out.println("eeee");
		model.addAttribute("name","SpringBlog from Brightyoun");
		
		return "hello";
	}
	
	@RequestMapping("/blog")
	public String blog(Model model){
		return "blog";
	}
	
	@RequestMapping("/post")
	public String post(Model model){
		return "model";
	}
}
