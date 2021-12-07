package com.enquero.EnqueroSocialMediaApp.dao;

import java.util.List;

import com.enquero.EnqueroSocialMediaApp.models.Like;

public interface LikeDao {

public Like addNewLike(Like like);
	
public List<Like> getLikesByPost(long postId);

public String deleteLikeByUserPost(String loggedInUserEmail,long postid);

public boolean checkPostLiked(String loggedInuserEmail,long postId);


}
