package com.enquero.EnqueroSocialMediaApp.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enquero.EnqueroSocialMediaApp.dao.CommentDao;
import com.enquero.EnqueroSocialMediaApp.dao.LikeDao;
import com.enquero.EnqueroSocialMediaApp.dao.PostDao;
import com.enquero.EnqueroSocialMediaApp.dao.UsersDao;
import com.enquero.EnqueroSocialMediaApp.helpers.LoginInput;
import com.enquero.EnqueroSocialMediaApp.helpers.UserActionException;
import com.enquero.EnqueroSocialMediaApp.models.Comment;
import com.enquero.EnqueroSocialMediaApp.models.Like;
import com.enquero.EnqueroSocialMediaApp.models.Post;
import com.enquero.EnqueroSocialMediaApp.models.User;

@Service
public class SocialMediaAppServiceImpl implements SocialMediaAppService {

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private PostDao postDao;

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private LikeDao likeDao;

	SimpleDateFormat sdf = new SimpleDateFormat();

	Logger logger = LoggerFactory.getLogger(SocialMediaAppService.class);

	@Override
	public User saveNewUser(User user) {
		// TODO Auto-generated method stub
		boolean checkIfExisting = usersDao.checkUserExists(user.getEmail());
		if (checkIfExisting) {
			throw new UserActionException("user already exists");
		}
		User newUser = usersDao.newUserRegister(user);

		return newUser;
	}

	@Override
	public User getUserByEmail(String userEmail) {
		// TODO Auto-generated method stub
		User user = usersDao.getUserByEmail(userEmail);
		if (user == null) {
			throw new UserActionException("user not found");
		}
		return user;
	}

	@Override
	public String checkUserIsAuth(LoginInput loginInput) {
		// TODO Auto-generated method stub
		String response = usersDao.isUserAuthenticated(loginInput);
		if (response.equals("no")) {
			throw new UserActionException("The email and password combination is incorrect");

		}
		return "yes";
	}

	@Override
	public String checkExistingUser(String email) {
		// TODO Auto-generated method stub
		boolean check = usersDao.checkUserExists(email);
		if (check) {
			throw new UserActionException("user already exists. please login with the same.");
		}
		return "no";
	}

	@Override
	public User updateUserPassword(String email, String password) {
		// TODO Auto-generated method stub
		User user = usersDao.updateUserPassword(email, password);
		if (user == null) {
			throw new UserActionException("invalid user email");
		}
		return user;
	}

	@Override
	public List<Post> getHomePagePosts() {
		// TODO Auto-generated method stub
		List<Post> homePagePosts = postDao.getPostsForHomePage();
		return homePagePosts;
	}

	@Override
	public Post addNewPost(Post post) {
		// TODO Auto-generated method stub
		post.setPostId(SequenceGeneratorService.generateSequence(Post.SEQUENCE_NAME));
		post.setPostedTime(String.valueOf(sdf.format(System.currentTimeMillis())));
		Pattern MY_PATTERN = Pattern.compile("#(\\S+)");
		Matcher mat = MY_PATTERN.matcher(post.getPostCaption());
		List<String> hastags=new ArrayList<String>();
		while (mat.find()) {
		  hastags.add(mat.group(1));
		}
		post.setHashtags(hastags);
		Post newPost = postDao.addNewPost(post);
		return newPost;
	}

	@Override
	public Comment addNewComment(Comment comment) {
		// TODO Auto-generated method stub
		comment.setCommentTime(String.valueOf(sdf.format(System.currentTimeMillis())));
		Comment newComment = commentDao.addNewComment(comment);
		return newComment;
	}

	@Override
	public List<Comment> getCommentsByPost(long postId) {
		// TODO Auto-generated method stub
		List<Comment> commentsByPosts = commentDao.getCommentsByPost(postId);
		return commentsByPosts;
	}

	@Override
	public String deleteCommentByUserPosted(long commentId) {
		// TODO Auto-generated method stub
		String response = commentDao.deleteCommentByUserPosted(commentId);
		return response;
	}

	@Override
	public Like addNewLike(Like like) {
		getUserByEmail(like.getLikeUserEmail());
		Like newLike = likeDao.addNewLike(like);
		return newLike;
	}

	@Override
	public List<Like> getLikesByPost(long postId) {
		// TODO Auto-generated method stub
		postDao.getPostById(postId);
		List<Like> likesByPost = likeDao.getLikesByPost(postId);
		return likesByPost;
	}

	@Override
	public String deleteLikeByUserPost(String userEmail, long postId) {
		getUserByEmail(userEmail);
		String response = likeDao.deleteLikeByUserPost(userEmail, postId);
		return response;
	}

	@Override
	public boolean checkPostLiked(String loggedInuserEmail, long postId) {
		// TODO Auto-generated method stub
		postDao.getPostById(postId);
		getUserByEmail(loggedInuserEmail);
		boolean response = likeDao.checkPostLiked(loggedInuserEmail, postId);
		return response;
	}

	@Override
	public List<Post> getPostsByHashtag(String queryString) {
		// TODO Auto-generated method stub
		List<Post> posts = postDao.getPostsByHashtag(queryString);
		return posts;
	}

	@Override
	public List<Post> getPostsByUserPosted(String userEmail) {
		// TODO Auto-generated method stub
		getUserByEmail(userEmail);
		List<Post> posts = postDao.getPostsByUserPosted(userEmail);
		return posts;	}

	@Override
	public Post updatePost(Post post) {
		// TODO Auto-generated method stub
		Pattern MY_PATTERN = Pattern.compile("#(\\S+)");
		Matcher mat = MY_PATTERN.matcher(post.getPostCaption());
		List<String> hastags=new ArrayList<String>();
		while (mat.find()) {
		  hastags.add(mat.group(1));
		}
		post.setHashtags(hastags);
		Post updatedPost = postDao.updatePost(post);
		return updatedPost;
	}

}
