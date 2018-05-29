package com.planetclubs.model;

import java.io.Serializable;

public class Login implements Serializable{

	private static final long serialVersionUID = 1L;

    private String username;
	
	private String password;
	
	private String userType;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
}
