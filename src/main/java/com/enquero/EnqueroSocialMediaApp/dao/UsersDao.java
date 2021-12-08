package com.enquero.EnqueroSocialMediaApp.dao;

import com.enquero.EnqueroSocialMediaApp.helpers.LoginInput;
import com.enquero.EnqueroSocialMediaApp.helpers.LoginUserDetails;
import com.enquero.EnqueroSocialMediaApp.models.User;

public interface UsersDao {

	public User newUserRegister(User user);

	public boolean checkUserExists(String userEmail);

	public String isUserAuthenticated(LoginInput loginInput);

	public User getUserByEmail(String email);

	public User updateUserPassword(String email, String password);
	
	//public LoginUserDetails getUserByEmail();
	
	

}
