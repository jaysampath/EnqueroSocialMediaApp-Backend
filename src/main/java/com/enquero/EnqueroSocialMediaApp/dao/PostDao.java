package com.enquero.EnqueroSocialMediaApp.dao;

import java.util.List;

import com.enquero.EnqueroSocialMediaApp.models.Post;

public interface PostDao {
	
	public List<Post> getPostsForHomePage();
	
	public Post addNewPost(Post post);
	
	public Post getPostById(long postId);
	
	public List<Post> getPostsByHashtag(String queryString);
	
	public List<Post> getPostsByUserPosted(String userEmail);
	
	public Post updatePost(Post post);

}
