package com.enquero.EnqueroSocialMediaApp.dao;

import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.enquero.EnqueroSocialMediaApp.helpers.PostActionException;
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

	@Override
	public Post getPostById(long postId) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("postId").is(postId));
		List<Post> posts = mongoTemplate.find(query,Post.class, "post");
		if(posts.size()==0) {
			throw new PostActionException("PostActionException: invalid post Id");
		}
		return posts.get(0);
	}

	@Override
	public List<Post> getPostsByHashtag(String queryString) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("hashtags").is(queryString));
		List<Post> posts = mongoTemplate.find(query,Post.class, "post");
		return posts;
	}

	@Override
	public List<Post> getPostsByUserPosted(String userEmail) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("userEmail").is(userEmail));
		List<Post> posts = mongoTemplate.find(query,Post.class, "post");
		return posts;
	}

	@Override
	public Post updatePost(Post post) {
		// TODO Auto-generated method stub
		Post updatedPost = mongoTemplate.save(post, "post");
		return updatedPost;
	}

}
