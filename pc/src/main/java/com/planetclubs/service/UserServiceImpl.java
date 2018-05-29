package com.planetclubs.service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.planetclubs.dao.CorporateDAO;
import com.planetclubs.dao.MembershipDAO;
import com.planetclubs.dao.UserDAO;
import com.planetclubs.model.Corporate;
import com.planetclubs.model.Login;
import com.planetclubs.model.Membership;
import com.planetclubs.model.Users;
import com.planetclubs.util.Constants;
import com.planetclubs.util.SendEmail;

import in.planetclubs.LoginType;
import in.planetclubs.UserType;

@Component
@Path("/userservice")
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private CorporateDAO corporateDAO;
	
	@Autowired
	private MembershipDAO membershipDAO;
	
	// Basic CRUD operations for User Service

	@POST
	@Path("adduser")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public UserType createOrSaveUserInfo(UserType userType) {
		Corporate corporate = new Corporate();
		if(userType.getCorporateId()!=0){
			corporate = corporateDAO.getCorporate(userType.getCorporateId());
			if(corporate.isDoubleVerification()){
				userType.setCompVerified(false);
			}
		}
		
		Users user = new Users();
		user.setUserId(userType.getUserId());
		user.setContactNo(userType.getContactNo());
		user.setEmail(userType.getEmail());
		user.setFirstName(userType.getFirstName());
		user.setLastName(userType.getLastName());
		user.setGender(userType.getGender());
		user.setPassword(userType.getPassword());
		user.setUserType(userType.getUserType());
		user.setVerified(userType.isVerified());
		userType.setCompVerified(userType.isCompVerified());
		user.setToken(userType.getToken());
		user.setCorporateId(userType.getCorporateId());
		user.setReferalCode(userType.getReferalCode());
		
		Users getUser =  userDAO.insertNewUserInfo(user);
		
		
		SendEmail emailSender = new SendEmail();
		emailSender.sendEmailToUser(getUser.getEmail(), getUser.getFirstName() + " " +getUser.getLastName(),getUser.getToken());
		
		if(user.getCorporateId()!=0){
			if(corporate.isDoubleVerification()){
				emailSender.sendCorporateEmailToUser(corporate.getContactEmail(), getUser.getFirstName() + " " +getUser.getLastName(),getUser.getToken());
			}
			
		}
		
		Membership membership = new Membership();
		String membershipNo = "PC-" + getUser.getFirstName().substring(0,1) + getUser.getLastName().substring(0,1)+"IN"+getUser.getUserId();
		membership.setMembershipNo(membershipNo);
		membership.setMembershipType("Bronze");
		Date now = new Date();
		
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(now);
		startCal.setTimeZone(TimeZone.getTimeZone("IST"));
		
		Date start = startCal.getTime();
		
		membership.setStartDate(start);
		startCal.set(Calendar.YEAR,Calendar.YEAR+3);
		Date end = startCal.getTime();
		membership.setEndDate(end);
		
		membership.setUpdateDate(start);
		membership.setTotalAddonPoints(0);
		membership.setUserId(getUser.getUserId());
		membership.setActive(1);
		membership.setCorporateEmail("");
		membership.setChunks(0);
		
		
		membershipDAO.insertNewMembershipInfo(membership);
		
		UserType users = new UserType();
		if(getUser !=null){
			
			users.setUserId(getUser.getUserId());
			users.setContactNo(getUser.getContactNo());
			users.setEmail(getUser.getEmail());
			users.setFirstName(getUser.getFirstName());
			users.setLastName(getUser.getLastName());
			users.setPassword(getUser.getPassword());
			users.setGender(getUser.getGender());
			users.setUserType(getUser.getUserType());
			users.setVerified(getUser.isVerified());
			users.setCompVerified(getUser.isCompVerified());
			users.setToken(getUser.getToken());
			users.setCorporateId(getUser.getCorporateId());
			users.setReferalCode(getUser.getReferalCode());
			
		}
		
		return users;
	}

	// http://localhost:8080/Jersey-Spring-Hibernate/rest/userservice/getuser/1
	@GET
	@Path("getuser/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public UserType getUserInfo(@PathParam("id") int userId) {

		Users getUser = userDAO.getUserInfo(userId);
		
		UserType userType = new UserType();
		userType.setUserId(getUser.getUserId());
		userType.setContactNo(getUser.getContactNo());
		userType.setEmail(getUser.getEmail());
		userType.setFirstName(getUser.getFirstName());
		userType.setLastName(getUser.getLastName());
		userType.setGender(getUser.getGender());
		userType.setPassword(getUser.getPassword());
		userType.setUserType(getUser.getUserType());
		userType.setVerified(getUser.isVerified());
		userType.setCompVerified(getUser.isCompVerified());
		userType.setToken(getUser.getToken());
		userType.setCorporateId(getUser.getCorporateId());
		userType.setReferalCode(getUser.getReferalCode());
		
		return userType;
	}
	
	@GET
	@Path("checkuser/{email}/{isCorporate}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String checkIfEmailExist(@PathParam("email") String email,@PathParam("isCorporate") String isCorporate) {

		if(email.isEmpty() || "".equalsIgnoreCase(email)){
			return Constants.FAILURE;
		}
		String userExist = userDAO.checkIfEmailExist(email);
		if(Constants.SUCCESS.equalsIgnoreCase(userExist) && "false".equalsIgnoreCase(isCorporate)){
			return userExist;
		}
		else if("true".equalsIgnoreCase(isCorporate)){
			
			List<Corporate> lstCorporate = corporateDAO.getAllCorporatesInfo();
			int index = email.indexOf('@');
			String domain = email.substring(index,email.length());
			for (Iterator<Corporate> iterator = lstCorporate.iterator(); iterator.hasNext();) {
				Corporate corporate = iterator.next();
				List<String> domainList = Arrays.asList(corporate.getEmailDomain().split(","));
				for (String dom: domainList){
					if(domain.equalsIgnoreCase(dom)){
						return String.valueOf(corporate.getId());
					}

				}
			}
			return Constants.CORPORATE_NOT_FOUND; 

		}
		
		return Constants.FAILURE;
	}
	
	@GET
	@Path("checkreferal/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String checkIfUserIdExist(@PathParam("id") String userId) {

		if(userId.contains("PCREFER")){
			userId = userId.replace("PCREFER", "");
			return userDAO.checkIfUserIdExist(userId);
		}else {
			return "Failure";
		}
		
		
	}
	
	@GET
	@Path("checknumber/{number}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String checkIfNumberExist(@PathParam("number") String number) {

		return String.valueOf(userDAO.checkIfNumberExist(number));
		
	}

	@POST
	@Path("updateuser")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateUserInfo(UserType userType) {

		Users modifyUser = new Users();
		modifyUser.setUserId(userType.getUserId());
		modifyUser.setContactNo(userType.getContactNo());
		modifyUser.setEmail(userType.getEmail());
		modifyUser.setFirstName(userType.getFirstName());
		modifyUser.setLastName(userType.getLastName());
		modifyUser.setGender(userType.getGender());
		modifyUser.setPassword(userType.getPassword());
		modifyUser.setUserType(userType.getUserType());
		modifyUser.setVerified(userType.isVerified());
		modifyUser.setCompVerified(userType.isCompVerified());
		modifyUser.setToken(userType.getToken());
		modifyUser.setCorporateId(userType.getCorporateId());
		userType.setReferalCode(userType.getReferalCode());
		
		return userDAO.updateUserInfo(modifyUser);
	}

	@POST
	@Path("loginuser")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public UserType loginuser(LoginType loginType) {

		Login login = new Login();
		login.setUsername(loginType.getUsername());
		login.setPassword(loginType.getPassword());
		login.setUserType(loginType.getUserType());
		
		Users getUser = userDAO.loginUser(login);
		if(getUser !=null){
			UserType userType = new UserType();
			userType.setUserId(getUser.getUserId());
			userType.setContactNo(getUser.getContactNo());
			userType.setEmail(getUser.getEmail());
			userType.setFirstName(getUser.getFirstName());
			userType.setLastName(getUser.getLastName());
			userType.setGender(getUser.getGender());
			userType.setPassword(getUser.getPassword());
			userType.setUserType(getUser.getUserType());
			userType.setVerified(getUser.isVerified());
			userType.setCompVerified(getUser.isCompVerified());
			userType.setToken(getUser.getToken());
			userType.setCorporateId(getUser.getCorporateId());
			userType.setReferalCode(getUser.getReferalCode());
			return userType;

		}
		return null;
	}
	
	@POST
	@Path("resetpassword")
	@Consumes({"application/xml", "application/json", "application/x-www-form-urlencoded"})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void resetPassword(@FormParam("password") String password, @FormParam("token") String token) {	
		
		Users user = userDAO.getUserInfoByEmailToken(token);
		if(user != null){
			user.setPassword(password);
			user.setResetPassword(false);
			user.setResetPasswordToken("");
			userDAO.updateUserInfo(user);
		}
	}

}