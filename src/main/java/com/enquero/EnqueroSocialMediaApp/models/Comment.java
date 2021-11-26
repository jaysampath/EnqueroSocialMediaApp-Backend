package com.enquero.EnqueroSocialMediaApp.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comment")
public class Comment {

	@Transient
	public final String SEQUENCE_NAME = "comment_sequence";

	@Id
	@Indexed
	private int id;

	private int postId;

	private String comment;

	private String commentTime;

	private String commentedUserEmail;

	private String commentedUsername;

	private String commentedUserProfilePicUrl;
	
	public Comment() {
		
	}

	public Comment(int postId, String comment, String commentTime, String commentedUserEmail, String commentedUsername,
			String commentedUserProfilePicUrl) {
		this.postId = postId;
		this.comment = comment;
		this.commentTime = commentTime;
		this.commentedUserEmail = commentedUserEmail;
		this.commentedUsername = commentedUsername;
		this.commentedUserProfilePicUrl = commentedUserProfilePicUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}

	public String getCommentedUserEmail() {
		return commentedUserEmail;
	}

	public void setCommentedUserEmail(String commentedUserEmail) {
		this.commentedUserEmail = commentedUserEmail;
	}

	public String getCommentedUsername() {
		return commentedUsername;
	}

	public void setCommentedUsername(String commentedUsername) {
		this.commentedUsername = commentedUsername;
	}

	public String getCommentedUserProfilePicUrl() {
		return commentedUserProfilePicUrl;
	}

	public void setCommentedUserProfilePicUrl(String commentedUserProfilePicUrl) {
		this.commentedUserProfilePicUrl = commentedUserProfilePicUrl;
	}

	public String getSEQUENCE_NAME() {
		return SEQUENCE_NAME;
	}
	
	

}
