package com.enquero.EnqueroSocialMediaApp.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enquero.EnqueroSocialMediaApp.helpers.LoginInput;
import com.enquero.EnqueroSocialMediaApp.helpers.UserActionException;
import com.enquero.EnqueroSocialMediaApp.models.Post;
import com.enquero.EnqueroSocialMediaApp.models.User;
import com.enquero.EnqueroSocialMediaApp.dao.PostDao;
import com.enquero.EnqueroSocialMediaApp.dao.UsersDao;


@Service
public class SocialMediaAppServiceImpl implements SocialMediaAppService {
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private PostDao postDao;
	

	SimpleDateFormat sdf = new SimpleDateFormat();

	Logger logger = LoggerFactory.getLogger(SocialMediaAppService.class);
	
	
	@Override
	public User saveNewUser(User user) {
		// TODO Auto-generated method stub
		boolean checkIfExisting = usersDao.checkUserExists(user.getEmail());
		if(checkIfExisting) {
			throw new UserActionException("user already exists");
		}
		User newUser = usersDao.newUserRegister(user);
		
		return newUser;
	}
	
	@Override
	public User getUserByEmail(String userEmail) {
		// TODO Auto-generated method stub
		User user = usersDao.getUserByEmail(userEmail);
		if(user==null) {
			throw new UserActionException("user not found");
		}
		return user;
	}
	
	@Override
	public String checkUserIsAuth(LoginInput loginInput) {
		// TODO Auto-generated method stub
		String response = usersDao.isUserAuthenticated(loginInput);
		if(response.equals("no")) {
			throw new UserActionException("The email and password combination is incorrect");
			
		}
		return "yes";
	}
	
	@Override
	public String checkExistingUser(String email) {
		// TODO Auto-generated method stub
		boolean check = usersDao.checkUserExists(email);
		if(check) {
			throw new UserActionException("user already exists. please login with the same.");
		}
		return "no";
	}
	
	@Override
	public User updateUserPassword(String email, String password) {
		// TODO Auto-generated method stub
		User user = usersDao.updateUserPassword(email, password);
		if(user==null) {
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
		Post newPost = postDao.addNewPost(post);
		return newPost;
	}



}
