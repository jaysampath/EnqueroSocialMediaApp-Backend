package com.enquero.EnqueroSocialMediaApp.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.enquero.EnqueroSocialMediaApp.models.Post;

@Repository
public class PostDaoImpl implements PostDao {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	Logger logger = LoggerFactory.getLogger(UsersDaoImpl.class);

	@Override
	public List<Post> getPostsForHomePage() {
		// TODO Auto-generated method stub
		Query query = new Query();
		List<Post> homePagePosts  = mongoTemplate.find(query, Post.class);
		return homePagePosts;
	}

	@Override
	public Post addNewPost(Post post) {
		// TODO Auto-generated method stub
		
		Post newPost = mongoTemplate.save(post, "post");
		return newPost;
	}

}
