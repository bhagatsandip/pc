package com.planetclubs.service;

import in.planetclubs.LoginType;
import in.planetclubs.UserType;

public interface IUserService {

	public UserType createOrSaveUserInfo(UserType user);
	public UserType getUserInfo(int id);
	public String updateUserInfo(UserType user);
	public UserType loginuser(LoginType loginType);
	public String checkIfEmailExist(String email,String isCorporate);
	public String checkIfNumberExist(String number);
	public String checkIfUserIdExist(String userId);
	public void resetPassword(String password,String token);

}
