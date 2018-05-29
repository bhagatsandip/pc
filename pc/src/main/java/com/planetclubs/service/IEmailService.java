package com.planetclubs.service;

public interface IEmailService {

	public void sendEmailToUser(String emailId,String name,String token);
	
	public void verifyEmail(String token);
}
