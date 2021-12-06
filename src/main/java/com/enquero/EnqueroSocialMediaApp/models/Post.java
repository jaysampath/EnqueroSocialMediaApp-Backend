package com.enquero.EnqueroSocialMediaApp.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "post")
public class Post {

	@Transient
	public static final String SEQUENCE_NAME = "post_sequence";

	@Id
	@Indexed
	private long postId;

	private String userEmail;

	private String postUsername;

	private String postUserProfilePicUrl;

	private String postType;

	private String postUrl;

	private String postCaption;

	private String postedTime;

	private long likes;

	private long comments;

	public Post() {

	}

	public Post(String userEmail, String postUsername, String postUserProfilePicUrl, String postType, String postUrl,
			String postCaption, String postedTime, long likes, long comments) {
		this.userEmail = userEmail;
		this.postUsername = postUsername;
		this.postUserProfilePicUrl = postUserProfilePicUrl;
		this.postType = postType;
		this.postUrl = postUrl;
		this.postCaption = postCaption;
		this.postedTime = postedTime;
		this.likes = likes;
		this.comments = comments;
	}

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	public String getPostUrl() {
		return postUrl;
	}

	public void setPostUrl(String postUrl) {
		this.postUrl = postUrl;
	}

	public String getPostCaption() {
		return postCaption;
	}

	public void setPostCaption(String postCaption) {
		this.postCaption = postCaption;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPostUsername() {
		return postUsername;
	}

	public void setPostUsername(String postUsername) {
		this.postUsername = postUsername;
	}

	public String getPostUserProfilePicUrl() {
		return postUserProfilePicUrl;
	}

	public void setPostUserProfilePicUrl(String postUserProfilePicUrl) {
		this.postUserProfilePicUrl = postUserProfilePicUrl;
	}

	public String getPostedTime() {
		return postedTime;
	}

	public void setPostedTime(String postedTime) {
		this.postedTime = postedTime;
	}

	public long getLikes() {
		return likes;
	}

	public void setLikes(long likes) {
		this.likes = likes;
	}

	public long getComments() {
		return comments;
	}

	public void setComments(long comments) {
		this.comments = comments;
	}


}
