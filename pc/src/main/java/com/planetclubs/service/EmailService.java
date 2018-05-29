package com.planetclubs.service;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.planetclubs.dao.MembershipDAO;
import com.planetclubs.dao.UsageDAO;
import com.planetclubs.dao.UserDAO;
import com.planetclubs.model.Membership;
import com.planetclubs.model.Usage;
import com.planetclubs.model.Users;
import com.planetclubs.util.Constants;
import com.planetclubs.util.SendEmail;
import com.planetclubs.util.Utils;

import in.planetclubs.AlertType;


@Component
@Path("/emailservice")
public class EmailService implements IEmailService {
	
	@Autowired
	private MembershipDAO membershipDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private Utils utils;
	
	@Autowired
	private IGeneralService generalService;

	@Autowired
	private UsageDAO usageDAO;

	@GET
	@Path("sendmail/{emailId}/{name}/{token}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void sendEmailToUser(@PathParam("emailId") String emailId,@PathParam("name") String name,@PathParam("token") String token) {

		// emailSender
		SendEmail emailSender = new SendEmail();
		emailSender.sendEmailToUser(emailId,name,token);

	}
	
	@GET
	@Path("resetpassword/{emailId}/{token}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void resetPassword(@PathParam("emailId") String emailId,@PathParam("token") String token) {

		Users user  = userDAO.getUserInfoByEmail(emailId);
		if(user != null){
			user.setResetPassword(true);
			user.setResetPasswordToken(token);
			userDAO.updateUserInfo(user);
			// emailSender
			SendEmail emailSender = new SendEmail();
			emailSender.resetPassword(emailId,token);
		}
	}
	
	@GET
	@Path("redeempointsrequest/{emailId}/{name}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void redeemPointsRequest(@PathParam("emailId") String emailId,@PathParam("name") String name) {

		// emailSender
		SendEmail emailSender = new SendEmail();
		emailSender.redeemPointsRequest(emailId,name);

	}

	@GET
	@Path("verifyemail/{token}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void verifyEmail(@PathParam("token") String token) {
		
		Users user = userDAO.getUserByToken(token);
		Map<String,String> map = generalService.getReferenceData();
		if(user != null && !user.isVerified()){
			
			java.net.URI location = null;
			try {
				location = new java.net.URI("http://www.planetclubs.in/#/verify");
				if(!user.getReferalCode().isEmpty()){
					String referalCode = user.getReferalCode();
					if(referalCode.contains(Constants.REFERAL_STRING)){
						int id = Integer.parseInt(referalCode.substring(Constants.REFERAL_STRING.length()));
						Membership membership = membershipDAO.getMembershipInfo(id);
						if(membership!=null){
							Usage usage = new Usage();
							usage.setClubId(1);
							
							usage.setDetails("Referal Points Added");
							usage.setIsApproved("BuyPoints");
							usage.setUserId(id);
							usage.setServiceType("system");
							usage.setServiceUnitCost(Integer.parseInt(map.get(Constants.REFERAL_AMOUNT)));
							Date start = utils.getCurrentTime();
							usage.setStartDateTime(start);
							usage.setEndDateTime(start);
							usageDAO.insertNewUsageInfo(usage);
							membership.setTotalAddonPoints(membership.getTotalAddonPoints()+Integer.parseInt(map.get(Constants.REFERAL_AMOUNT)));
							
							membershipDAO.updateMembershipInfo(membership);
						}

					}
				}
				Membership membership = membershipDAO.getMembershipInfo(user.getUserId());
				if(membership!=null){
					membership.setTotalAddonPoints(Integer.parseInt(map.get(Constants.SIGNIN_AMOUNT)));
					membershipDAO.updateMembershipInfo(membership);
					Usage usage1 = new Usage();
					usage1.setClubId(1);
					usage1.setDetails("SignIn Bonus");
					usage1.setIsApproved("BuyPoints");
					usage1.setUserId(user.getUserId());
					usage1.setServiceType("system");
					usage1.setServiceUnitCost(Integer.parseInt(map.get(Constants.SIGNIN_AMOUNT)));
					Date start1 = utils.getCurrentTime();
					usage1.setStartDateTime(start1);
					usage1.setEndDateTime(start1);
					usageDAO.insertNewUsageInfo(usage1);
				}
				
				
				user.setReferalCode("");
				user.setVerified(true);
				userDAO.updateUserInfo(user);
				
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        throw new WebApplicationException(Response.temporaryRedirect(location).build());
		}
		
	}
	
	@GET
	@Path("verifycorpemail/{token}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void verifycorpemail(@PathParam("token") String token) {
		
		Users user = userDAO.getUserByToken(token);
		if(user != null){
			
			java.net.URI location = null;
			try {
				location = new java.net.URI("http://www.planetclubs.in/#/verify");
				
				user.setCompVerified(true);
				userDAO.updateUserInfo(user);
				
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
	        throw new WebApplicationException(Response.temporaryRedirect(location).build());
		}
		
	}
	
	
	@POST
	@Path("contactus")
	@Consumes({"application/xml", "application/json", "application/x-www-form-urlencoded"})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void contactus(@FormParam("name") String name, @FormParam("email") String email,
			@FormParam("category") String category, @FormParam("message") String message) {
		
		// emailSender
				SendEmail emailSender = new SendEmail();
				emailSender.contactus(name,email, category, message);
		
	}
	
	@POST
	@Path("enrollclub")
	@Consumes({"application/xml", "application/json", "application/x-www-form-urlencoded"})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void enrollclub(@FormParam("name") String name,@FormParam("clubType") String clubType,@FormParam("pincode") String pincode,
			@FormParam("telephone") String telephone, @FormParam("email") String email) {
		
		// emailSender
				SendEmail emailSender = new SendEmail();
				emailSender.enrollclub(name,clubType, pincode, telephone, email);
		
	}
	
	@POST
	@Path("enrollcorporate")
	@Consumes({"application/xml", "application/json", "application/x-www-form-urlencoded"})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void enrollcorporate(@FormParam("name") String name,@FormParam("city") String city,@FormParam("category") String category,
			@FormParam("contact") String contact, @FormParam("email") String email,@FormParam("number") String number) {
		
		// emailSender
				SendEmail emailSender = new SendEmail();
				emailSender.enrollcorporate(name,city, category, contact, email,number);
		
	}
	
	@POST
	@Path("alert")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void alert(AlertType alertType) {
		
		// emailSender
				SendEmail emailSender = new SendEmail();
				emailSender.alert(alertType.getUserName(), alertType.getSubject(), alertType.getStackTrace());
		
	}
	
	
}
