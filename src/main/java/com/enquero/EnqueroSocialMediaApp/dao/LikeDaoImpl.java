package com.enquero.EnqueroSocialMediaApp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.enquero.EnqueroSocialMediaApp.helpers.LikeActionException;
import com.enquero.EnqueroSocialMediaApp.models.Like;
import com.enquero.EnqueroSocialMediaApp.models.Post;
import com.enquero.EnqueroSocialMediaApp.service.SequenceGeneratorService;
import com.mongodb.client.result.DeleteResult;

@Repository
public class LikeDaoImpl implements LikeDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Like addNewLike(Like like) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(new Criteria().andOperator(Criteria.where("postId").is(like.getPostId()),
				Criteria.where("likeUserEmail").is(like.getLikeUserEmail())));
		List<Like> likes = mongoTemplate.find(query, Like.class, "Like");
		if(likes.size()>0) {
			return like;
		}
		like.setId(SequenceGeneratorService.generateSequence(Like.SEQUENCE_NAME));
		mongoTemplate.save(like, "Like");

		updateLikesCount(like.getPostId(), 1);
		return like;
	}

	@Override
	public List<Like> getLikesByPost(long postId) {
		// TODO Auto-generated method stub

		Query query = new Query();
		query.addCriteria(Criteria.where("postId").is(postId));
		List<Like> likesByPost = mongoTemplate.find(query, Like.class, "Like");
		return likesByPost;
	}

	@Override
	public String deleteLikeByUserPost(String loggedInUserEmail, long postId) {

		Query query = new Query();
		query.addCriteria(new Criteria().andOperator(Criteria.where("postId").is(postId),
				Criteria.where("likeUserEmail").is(loggedInUserEmail)));
		List<Like> likes = mongoTemplate.find(query, Like.class, "Like");
		if (likes.size() == 0) {
			//throw new LikeActionException("LikeActionException: The user haven't liked this.");
			return "not-liked";
		}
		DeleteResult like = mongoTemplate.remove(query, Like.class, "Like");
		updateLikesCount(likes.get(0).getPostId(), -1);
		if (like != null) {
			return "deleted";
		}
		return "error";
	}

	public boolean updateLikesCount(long postId, int action) {
		Query query = new Query();
		query.addCriteria(Criteria.where("postId").is(postId));
		List<Post> posts = mongoTemplate.find(query, Post.class, "post");
        //System.out.println(posts);
		Post post = posts.get(0);
		long prevLikes = post.getLikes();
		if (action == 1) {
			post.setLikes(prevLikes+1);
		} else {
			post.setLikes(prevLikes-1);
		}

		// Query updateQuery = new Query();
		Post updatedPost = mongoTemplate.save(post, "post");
		if (updatedPost != null) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean checkPostLiked(String loggedInuserEmail, long postId) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("postId").is(postId));
		List<Like> likes = mongoTemplate.find(query, Like.class, "Like");
		for (Like like : likes) {
			if (like.getLikeUserEmail().equalsIgnoreCase(loggedInuserEmail)) {
				return true;
			}
		}
		return false;
	}

}
