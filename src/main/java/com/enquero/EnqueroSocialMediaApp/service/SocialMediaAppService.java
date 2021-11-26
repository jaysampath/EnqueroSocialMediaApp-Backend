package com.enquero.EnqueroSocialMediaApp.service;

import java.util.List;

import com.enquero.EnqueroSocialMediaApp.helpers.LoginInput;
import com.enquero.EnqueroSocialMediaApp.models.Post;
import com.enquero.EnqueroSocialMediaApp.models.User;



public interface SocialMediaAppService {
	
public User saveNewUser(User user);
	
	public User getUserByEmail(String userEmail);
	
	public String checkUserIsAuth(LoginInput loginInput);
	
	public String checkExistingUser(String email);
	
	public User updateUserPassword(String email,String password);
	
	public List<Post> getHomePagePosts();
	
	public Post addNewPost(Post post);

}
