package com.enquero.EnqueroSocialMediaApp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.enquero.EnqueroSocialMediaApp.helpers.CommentActionException;
import com.enquero.EnqueroSocialMediaApp.models.Comment;
import com.enquero.EnqueroSocialMediaApp.models.Post;
import com.enquero.EnqueroSocialMediaApp.service.SequenceGeneratorService;
import com.mongodb.client.result.DeleteResult;

@Repository
public class CommentDaoImpl implements CommentDao {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Comment addNewComment(Comment comment) {
		// TODO Auto-generated method stub
		comment.setId(SequenceGeneratorService.generateSequence(Comment.SEQUENCE_NAME));
		mongoTemplate.save(comment, "comment");
		updateCommentsCount(comment.getPostId(),1);
		return comment;
	}

	@Override
	public List<Comment> getCommentsByPost(long postId) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("postId").is(postId));
		List<Comment> commentsByPost = mongoTemplate.find(query, Comment.class, "comment");
		return commentsByPost;
	}
	

	@Override
	public String deleteCommentByUserPosted(long commentId) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(commentId));
		List<Comment> comments = mongoTemplate.find(query, Comment.class, "comment");
		if(comments.size()==0) {
			throw new CommentActionException("CommentActionException: invalid comment id");
		}
		DeleteResult comment = mongoTemplate.remove(query, Comment.class, "comment");
		updateCommentsCount(comments.get(0).getPostId(),-1);
		if(comment!=null) {
			return "deleted";
		}
		return "error";
	}
	
	
	public boolean updateCommentsCount(long postId, int action) {
		Query query = new Query();
		query.addCriteria(Criteria.where("postId").is(postId));
		List<Post> posts = mongoTemplate.find(query, Post.class, "post");
		System.out.println(postId);
		System.out.println(posts);
		Post post = posts.get(0);
		long prevComments = post.getComments();
		if(action==1) {
			post.setComments(prevComments+1);
		}else {
			post.setComments(prevComments-1);
		}
		
		//Query updateQuery = new Query();
		Post updatedPost = mongoTemplate.save(post, "post");
		if(updatedPost!=null) {
			return true;
		}else {
			return false;
		}
		
	}
	

}
