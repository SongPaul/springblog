package com.brightyoun.blog.presentation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.brightyoun.blog.domain.model.entity.Post;
import com.brightyoun.blog.infrastructure.dao.PostDao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostDao postDao;
	
	@Autowired
	private ConnectionRepository connectionRepository;
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String form(Post post){
		return "post/form";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@Valid Post post, BindingResult bindingResult) {
	    if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors());
	        return "post/form";
	    }
	    User user = getConnect();
	    Date from = new Date();
	    SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String date = transFormat.format(from);
	    
	    post.setRegDate(date);
	    post.setUserId(user.getProviderUserId());
	    post.setName(user.getDisplayName());
	    
	    return "redirect:/post/" + postDao.save(post).getId();
	}
	
	@RequestMapping("/list")
	public String list(Model model,
			@PageableDefault(sort={"id"}, direction = Direction.DESC, size = 2) Pageable pageable){
		List<Post> postList = postDao.findAll();
		
		Page<Post> postPage = postDao.findAll( pageable);
		model.addAttribute("postPage",postPage);
		User user = getConnect();
		model.addAttribute("user",user);
		model.addAttribute("postList",postList);
		return "post/blog";
	}
	
	@RequestMapping("/{id}")
	public String view(Model model, @PathVariable int id){
		Post post = postDao.findOne(id);
		User user = getConnect();
		model.addAttribute("user",user);
		model.addAttribute("post",post);
		return "post/post";
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editor(Model model, @PathVariable int id) {
		Post post = postDao.findOne(id);
		model.addAttribute("post", post);
		return "post/form";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String edit(@Valid Post post, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "post/form";
		}
		User user = getConnect();
		Post oldPost = postDao.findOne(post.getId());
		if(oldPost.getUserId().equals(user.getProviderUserId())){
			oldPost.setTitle(post.getTitle());
			oldPost.setSubtitle(post.getSubtitle());
			oldPost.setContent(post.getContent());
			return "redirect:/post/" + postDao.save(oldPost).getId();
		}
		return "post/form";
	}
	
	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable int id){
		User user = getConnect();
		Post post = postDao.findOne(id);
		if(post.getUserId().equals(user.getProviderUserId())){
			postDao.delete(id);
		}
		return "redirect:/post/post/list";
	}
	
	private User getConnect() {
		Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
		if (connection == null) {
			return null;
		}
		ConnectionData data = connection.createData();
		return new User(data.getProviderUserId(), data.getDisplayName());
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class User {
		String providerUserId;
		String displayName;
	}
}
