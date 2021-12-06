package com.enquero.EnqueroSocialMediaApp.dao;

import java.util.List;

import com.enquero.EnqueroSocialMediaApp.models.Comment;

public interface CommentDao {
	
	public Comment addNewComment(Comment comment);
	
	public List<Comment> getCommentsByPost(long postId);
	
	public String deleteCommentByUserPosted(long commentId);

}
