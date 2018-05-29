package com.planetclubs.dao;

import com.planetclubs.model.Login;
import com.planetclubs.model.Users;

public interface UserDAO {

	Users insertNewUserInfo(Users user);

	Users getUserInfo(int userId);
	
	Users getUserInfoByEmail(String emailId);
	
	Users getUserInfoByEmailToken(String token);

	String updateUserInfo(Users modifyUser);

	Users loginUser(Login login);

	String checkIfEmailExist(String email);
	
	boolean checkIfNumberExist(String number);

	Users getUserByToken(String token);

	String checkIfUserIdExist(String userId);

}
