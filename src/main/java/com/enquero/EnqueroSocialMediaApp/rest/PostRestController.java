package com.enquero.EnqueroSocialMediaApp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enquero.EnqueroSocialMediaApp.models.Post;
import com.enquero.EnqueroSocialMediaApp.service.SocialMediaAppService;

@RestController
@CrossOrigin
@RequestMapping("/post")
public class PostRestController {
	
	@Autowired
	private SocialMediaAppService socialMediaAppService;
	
	@GetMapping("/home")
	public List<Post> getHomePagePosts(){
		List<Post> homePagePosts = socialMediaAppService.getHomePagePosts();
		return homePagePosts;
	}
	
	@PostMapping("/add")
	public Post addNewPost(@RequestBody Post post) {
		Post newPost = socialMediaAppService.addNewPost(post);
		return newPost;
	}

}
