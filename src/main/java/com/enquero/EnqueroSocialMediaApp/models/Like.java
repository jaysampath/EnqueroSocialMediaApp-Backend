package com.enquero.EnqueroSocialMediaApp.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Like")
public class Like{

	@Transient
	public final String SEQUENCE_NAME = "like_sequence";

	@Id
	@Indexed
	private long id;

	private int postId;

	private int like;

	private String likeUserEmail;

	private String likeUsername;

	private String likeUserProfilePicUrl;
	
	public Like() {
		
	}
	public Like(int postId, int like, String likeTime, String likeUserEmail, String likeUsername,
			String likeUserProfilePicUrl) {
		this.postId = postId;
		this.like = like;
		this.likeUserEmail = likeUserEmail;
		this.likeUsername = likeUsername;
		this.likeUserProfilePicUrl = likeUserProfilePicUrl;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id ) {
		this.id = id ;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	

	public String getLikeUserEmail() {
		return likeUserEmail;
	}

	public void setLikeUserEmail(String likeUserEmail) {
		this.likeUserEmail = likeUserEmail;
	}

	public String getLikeUsername() {
		return likeUsername;
	}

	public void setLikeUsername(String likeUsername) {
		this.likeUsername = likeUsername;
	}

	public String getLikeUserProfilePicUrl() {
		return likeUserProfilePicUrl;
	}

	public void setLikeUserProfilePicUrl(String likeUserProfilePicUrl) {
		this.likeUserProfilePicUrl = likeUserProfilePicUrl;
	}


	
}