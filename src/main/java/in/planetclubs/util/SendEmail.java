package com.planetclubs.util;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.planetclubs.model.Corporate;
import com.planetclubs.model.Users;

import in.planetclubs.MembershipType;

public class SendEmail {
	
	String from = "pc-noreply@planetclubs.in";//change accordingly
    final String username = "pc-noreply@planetclubs.in";//change accordingly
    final String password = "DontAskMe@25";//change accordingly

    // Assuming you are sending email through relay.jangosmtp.net
    String host = "smtp.gmail.com";
   
	public void sendEmailToUser(String userEmail,String name,String token){
		
      // Recipient's email ID needs to be mentioned.
      String to = userEmail;//change accordingly

      // Sender's email ID needs to be mentioned
      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "587");

      // Get the Session object.
      Session session = Session.getInstance(props,
      new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
         }
      });

      try {
         // Create a default MimeMessage object.
       
    	  Message msg = new MimeMessage(session);
          msg.setFrom(new InternetAddress(from));

          // Set To: header field of the header.
          msg.setRecipients(Message.RecipientType.TO,
          InternetAddress.parse(to));

          // Set Subject: header field
          msg.setSubject("Verify your email now");
          
         // msg.setRecipients(Message.RecipientType.TO, email);
          MimeBodyPart body = new MimeBodyPart();
          MimeMultipart multipart = new MimeMultipart("related");
          //BodyPart messageBodyPart = new MimeBodyPart();
          String link = Constants.WEB_LINK+"rest/emailservice/verifyemail/"+token;
          
          String htmlMessage = "<html><img src='http://planetclubs-geeksbench.rhcloud.com/images/oie_transparent.png' ><br>";
     
          htmlMessage += "Hi " + name +",<br><br>";
          htmlMessage += "Thanks for showing interest  in <a href='http://www.planetclubs.in'> PlanetClubs.in<a><br><br>";
          htmlMessage += "Please verify your email address so we know that it's really you!";
          htmlMessage += "<br>Please click this <a href="+ link +">link<a> to activate your account:<br>";
          htmlMessage += "<br><br>Thanks,";
          htmlMessage += "<br>PlanetClubs Team";
          htmlMessage += "</html>";
          body.setText(htmlMessage, "US-ASCII", "html");
          //messageBodyPart.setContent(htmlMessage, "text/html");
          multipart.addBodyPart(body);
          msg.setSentDate(new Date()); 
          msg.setContent(multipart);
        //  msg.setText(htmlMessage);
         
          Transport.send(msg);

    	  /*  Message message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.setRecipients(Message.RecipientType.TO,
         InternetAddress.parse(to));

         // Set Subject: header field
         message.setSubject("Testing Subject");

         // Now set the actual message
         message.setText("Hello, this is sample for to check send "
            + "email using JavaMailAPI ");

         // Send message
         Transport.send(message);
*/
         System.out.println("Sent message successfully....");

      } catch (MessagingException e) {
            throw new RuntimeException(e);
      }
   }
	
	public void sendCorporateOTP(String userEmail, int otp){
		
	      // Recipient's email ID needs to be mentioned.
	      String to = userEmail;//change accordingly

	      // Sender's email ID needs to be mentioned
	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });

	      try {
	         // Create a default MimeMessage object.
	       
	    	  Message msg = new MimeMessage(session);
	          msg.setFrom(new InternetAddress(from));

	          // Set To: header field of the header.
	          msg.setRecipients(Message.RecipientType.TO,
	          InternetAddress.parse(to));

	          // Set Subject: header field
	          msg.setSubject("OTP for Membership Enrollment");
	          
	         // msg.setRecipients(Message.RecipientType.TO, email);
	          MimeBodyPart body = new MimeBodyPart();
	          MimeMultipart multipart = new MimeMultipart("related");
	          //BodyPart messageBodyPart = new MimeBodyPart();
	          
	          String htmlMessage = "<html><img src='http://planetclubs-geeksbench.rhcloud.com/images/oie_transparent.png' ><br>";
	          
	          htmlMessage += "Hi <br><br>";
	          htmlMessage += "Please enter below OTP to enroll for Planetclubs membership.";
	          htmlMessage += "<br><b>OTP</b> : " + otp;
	          htmlMessage += "<br>The OTP is valid for only half hour";
	          htmlMessage += "<br><br>Thanks,";
	          htmlMessage += "<br>PlanetClubs Team";
	          htmlMessage += "</html>";
	          body.setText(htmlMessage, "US-ASCII", "html");
	          //messageBodyPart.setContent(htmlMessage, "text/html");
	          multipart.addBodyPart(body);
	          msg.setSentDate(new Date()); 
	          msg.setContent(multipart);
	         
	          Transport.send(msg);


	         System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
	   }
		
	public void resetPassword(String userEmail,String token){
		
	      // Recipient's email ID needs to be mentioned.
	      String to = userEmail;//change accordingly

	      // Sender's email ID needs to be mentioned
	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });

	      try {
	         // Create a default MimeMessage object.
	       
	    	  Message msg = new MimeMessage(session);
	          msg.setFrom(new InternetAddress(from));

	          // Set To: header field of the header.
	          msg.setRecipients(Message.RecipientType.TO,
	          InternetAddress.parse(to));

	          // Set Subject: header field
	          msg.setSubject("Planet Clubs: Reset password Request.");
	          
	         // msg.setRecipients(Message.RecipientType.TO, email);
	          MimeBodyPart body = new MimeBodyPart();
	          MimeMultipart multipart = new MimeMultipart("related");
	          String link = Constants.WEB_LINK+"#/resetpassword?token="+token;
	          
	          String htmlMessage = "<html><img src='http://planetclubs-geeksbench.rhcloud.com/images/oie_transparent.png' ><br>";
	          
	          htmlMessage += "Hi " + ",<br><br>";
	          htmlMessage += "To reset your password, please click the link below and reset password.<br><br>";
	          htmlMessage += "<br>Please click this <a href="+ link +">link<a> to reset your password.<br>";
	          htmlMessage += "<br><br>Thanks,";
	          htmlMessage += "<br>PlanetClubs Team";
	          htmlMessage += "</html>";
	          body.setText(htmlMessage, "US-ASCII", "html");
	          multipart.addBodyPart(body);
	          msg.setSentDate(new Date()); 
	          msg.setContent(multipart);
	         
	          Transport.send(msg);

	         System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
	   }

	public void redeemPointsRequest(String emailId, String name) {
		// Recipient's email ID needs to be mentioned.
	      String to = "planetclubs@googlegroups.com";//change accordingly

	      // Sender's email ID needs to be mentioned
	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });

	      try {
	    	  
	    	  // Send Email to planet clubs Team.
	         // Create a default MimeMessage object.
	       
	    	  Message msg = new MimeMessage(session);
	          msg.setFrom(new InternetAddress(from));

	          // Set To: header field of the header.
	          msg.setRecipients(Message.RecipientType.TO,
	          InternetAddress.parse(to));

	          // Set Subject: header field
	          msg.setSubject("PlanetClubs Points Redeem Request.");
	          
	         // msg.setRecipients(Message.RecipientType.TO, email);
	          MimeBodyPart body = new MimeBodyPart();
	          MimeMultipart multipart = new MimeMultipart("related");
	          
	          String htmlMessage = "<html><img src='http://planetclubs-geeksbench.rhcloud.com/images/oie_transparent.png' ><br>";
	          
	          htmlMessage += "Hi Team, <br><br>";
	          htmlMessage += name +" is interested to reedeem his points. Please proceed with payments as per below details.";
	          htmlMessage += "<br> <br>Club Details:";
	          htmlMessage += "<br>Email : "+ emailId;
	          htmlMessage += "<br> <br>Cheers!";
	          htmlMessage += "</html>";
	          body.setText(htmlMessage, "US-ASCII", "html");
	          //messageBodyPart.setContent(htmlMessage, "text/html");
	          multipart.addBodyPart(body);
	          msg.setSentDate(new Date()); 
	          msg.setContent(multipart);
	        //  msg.setText(htmlMessage);
	         
	          Transport.send(msg);

		       
	    	  Message userMsg = new MimeMessage(session);
	    	  userMsg.setFrom(new InternetAddress(from));

	          // Set To: header field of the header.
	    	  userMsg.setRecipients(Message.RecipientType.TO,
	          InternetAddress.parse(emailId));

	          // Set Subject: header field
	    	  userMsg.setSubject("PlanetClubs Points Redeemption Request.");
	          
	         // msg.setRecipients(Message.RecipientType.TO, email);
	          MimeBodyPart userBody = new MimeBodyPart();
	          MimeMultipart usermultipart = new MimeMultipart("related");
	          
	          String userhtmlMessage = "<html><img src='http://planetclubs-geeksbench.rhcloud.com/images/oie_transparent.png' ><br>";
	          
	          userhtmlMessage += "Hi "+name+",<br><br>";
	          userhtmlMessage += "Your Request for points redeemption has been recorded. Our Team will get back to you in 48 hours.";
	          userhtmlMessage += "<br> <br>Thanks,";
	          userhtmlMessage += "<br>PlanetClubs Team";		          
	          userhtmlMessage += "</html>";
	          userBody.setText(userhtmlMessage, "US-ASCII", "html");
	          //messageBodyPart.setContent(htmlMessage, "text/html");
	          usermultipart.addBodyPart(userBody);
	          userMsg.setSentDate(new Date()); 
	          userMsg.setContent(usermultipart);
	        //  msg.setText(htmlMessage);
	         
	          Transport.send(userMsg);
	          
	         System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
		
	}
	
	public void contactus(String name,  String email, String category, String message) {
		// Recipient's email ID needs to be mentioned.
	      String to = "planetclubs@googlegroups.com";//change accordingly

	      // Sender's email ID needs to be mentioned

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });

	      try {
	    	  
	    	  // Send Email to planet clubs Team.
	         // Create a default MimeMessage object.
	       
	    	  Message msg = new MimeMessage(session);
	          msg.setFrom(new InternetAddress(from));

	          // Set To: header field of the header.
	          msg.setRecipients(Message.RecipientType.TO,
	          InternetAddress.parse(to));

	          // Set Subject: header field
	          msg.setSubject("PlanetClubs : " + name +"'s "+ category + " Recorded");
	          
	         // msg.setRecipients(Message.RecipientType.TO, email);
	          MimeBodyPart body = new MimeBodyPart();
	          MimeMultipart multipart = new MimeMultipart("related");
	          
	          String htmlMessage = "<html><img src='http://planetclubs-geeksbench.rhcloud.com/images/oie_transparent.png' ><br>";
	          
	          htmlMessage += "Hi Team, <br><br>";
	          htmlMessage += name + " has a " + category + " for Planet clubs." ;
	          htmlMessage += "<br> <br>User Details:";
	          htmlMessage += "<br>Name : "+ name;
	          htmlMessage += "<br>Email : "+ email;
	          htmlMessage += "<br>Category : "+ category;
	          htmlMessage += "<br>Message : "+ message;
	          htmlMessage += "<br> <br>Cheers!";
	          htmlMessage += "</html>";
	          body.setText(htmlMessage, "US-ASCII", "html");
	          //messageBodyPart.setContent(htmlMessage, "text/html");
	          multipart.addBodyPart(body);
	          msg.setSentDate(new Date()); 
	          msg.setContent(multipart);
	        //  msg.setText(htmlMessage);
	         
	          Transport.send(msg);

          
	          // Send Email to user.
		         // Create a default MimeMessage object.
		       
		    	  Message userMsg = new MimeMessage(session);
		    	  userMsg.setFrom(new InternetAddress(from));

		          // Set To: header field of the header.
		    	  userMsg.setRecipients(Message.RecipientType.TO,
		          InternetAddress.parse(email));

		          // Set Subject: header field
		    	  userMsg.setSubject("PlanetClubs : Thanks " + name +" for "+ category);
		          
		         // msg.setRecipients(Message.RecipientType.TO, email);
		          MimeBodyPart userBody = new MimeBodyPart();
		          MimeMultipart usermultipart = new MimeMultipart("related");
		          
		          String userhtmlMessage = "<html><img src='http://planetclubs-geeksbench.rhcloud.com/images/oie_transparent.png' ><br>";
		          
		          userhtmlMessage += "Hi "+name+",<br><br>";
		          userhtmlMessage += "Thanks for your valuable input. Our Team will get back to you in 48 hours.";
		          userhtmlMessage += "<br> <br>Thanks,";
		          userhtmlMessage += "<br>PlanetClubs Team";		          
		          userhtmlMessage += "</html>";
		          userBody.setText(userhtmlMessage, "US-ASCII", "html");
		          //messageBodyPart.setContent(htmlMessage, "text/html");
		          usermultipart.addBodyPart(userBody);
		          userMsg.setSentDate(new Date()); 
		          userMsg.setContent(usermultipart);
		        //  msg.setText(htmlMessage);
		         
		          Transport.send(userMsg);
	          
	         System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
		
	}

	public void enrollclub(String name, String clubType, String pincode, String telephone, String email) {
		// Recipient's email ID needs to be mentioned.
	      String to = "planetclubs@googlegroups.com";//change accordingly

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });

	      try {
	    	  
	    	  // Send Email to planet clubs Team.
	         // Create a default MimeMessage object.
	       
	    	  Message msg = new MimeMessage(session);
	          msg.setFrom(new InternetAddress(from));

	          // Set To: header field of the header.
	          msg.setRecipients(Message.RecipientType.TO,
	          InternetAddress.parse(to));

	          // Set Subject: header field
	          msg.setSubject("PlanetClubs : Club onboard Request Recorded");
	          
	         // msg.setRecipients(Message.RecipientType.TO, email);
	          MimeBodyPart body = new MimeBodyPart();
	          MimeMultipart multipart = new MimeMultipart("related");
	          
	          String htmlMessage = "<html><img src='http://planetclubs-geeksbench.rhcloud.com/images/oie_transparent.png' ><br>";
	          
	          htmlMessage += "Hi Team, <br><br>";
	          htmlMessage += name + " has raised request to onboard club." ;
	          htmlMessage += "<br> <br>Details:";
	          htmlMessage += "<br>Name : "+ name;
	          htmlMessage += "<br>Club Type : "+ clubType;
	          htmlMessage += "<br>Pincode : "+ pincode;
	          htmlMessage += "<br>Telephone : "+ telephone;
	          htmlMessage += "<br>Email : "+ email;
	          htmlMessage += "<br> <br>Cheers!";
	          htmlMessage += "</html>";
	          body.setText(htmlMessage, "US-ASCII", "html");
	          //messageBodyPart.setContent(htmlMessage, "text/html");
	          multipart.addBodyPart(body);
	          msg.setSentDate(new Date()); 
	          msg.setContent(multipart);
	        //  msg.setText(htmlMessage);
	         
	          Transport.send(msg);

	        if(email != "" && email.indexOf('@')> -1){
	        	
	        	// Send Email to user.
		         // Create a default MimeMessage object.
		       
		    	  Message userMsg = new MimeMessage(session);
		    	  userMsg.setFrom(new InternetAddress(from));
	
		          // Set To: header field of the header.
		    	  userMsg.setRecipients(Message.RecipientType.TO,
		          InternetAddress.parse(email));
	
		          // Set Subject: header field
		    	  userMsg.setSubject("PlanetClubs : Thanks " + name +" for Club Onboard Request");
		          
		         // msg.setRecipients(Message.RecipientType.TO, email);
		          MimeBodyPart userBody = new MimeBodyPart();
		          MimeMultipart usermultipart = new MimeMultipart("related");
		          
		          String userhtmlMessage = "<html><img src='http://planetclubs-geeksbench.rhcloud.com/images/oie_transparent.png' ><br>";
		          		          
		          userhtmlMessage += "Hi "+name+",<br><br>";
		          userhtmlMessage += "Thanks for Club Onboard Request. Our Team will get back to you in 48 hours.";
		          userhtmlMessage += "<br> <br>Thanks,";
		          userhtmlMessage += "<br>PlanetClubs Team";		          
		          userhtmlMessage += "</html>";
		          userBody.setText(userhtmlMessage, "US-ASCII", "html");
		          //messageBodyPart.setContent(htmlMessage, "text/html");
		          usermultipart.addBodyPart(userBody);
		          userMsg.setSentDate(new Date()); 
		          userMsg.setContent(usermultipart);
		        //  msg.setText(htmlMessage);
		         
		          Transport.send(userMsg);
	        	
	        }
	          
	          
	         System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
		
	}
	
	
	public void alert(String userName, String subject, String stackTrace) {
		// Recipient's email ID needs to be mentioned.
	      String to = "planetclubs@googlegroups.com";//change accordingly

	      // Sender's email ID needs to be mentioned

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });

	      try {
	    	  
	    	  // Send Email to planet clubs Team.
	         // Create a default MimeMessage object.
	       
	    	  Message msg = new MimeMessage(session);
	          msg.setFrom(new InternetAddress(from));

	          // Set To: header field of the header.
	          msg.setRecipients(Message.RecipientType.TO,
	          InternetAddress.parse(to));

	          // Set Subject: header field
	          msg.setSubject("PlanetClubs : " + subject );
	          
	         // msg.setRecipients(Message.RecipientType.TO, email);
	          MimeBodyPart body = new MimeBodyPart();
	          MimeMultipart multipart = new MimeMultipart("related");
	          
	          String htmlMessage = "<html><img src='http://planetclubs-geeksbench.rhcloud.com/images/oie_transparent.png' ><br>";
	          	          
	          htmlMessage += "Hi Team, <br><br>";
	          htmlMessage += userName + " has recorded an issue." ;
	          htmlMessage += "<br> <br>User Details:";
	          htmlMessage += "<br>Name : "+ userName;
	          htmlMessage += "<br>Stack Trace : "+ stackTrace;
	          htmlMessage += "</html>";
	          body.setText(htmlMessage, "US-ASCII", "html");
	          multipart.addBodyPart(body);
	          msg.setSentDate(new Date()); 
	          msg.setContent(multipart);
	         
	          Transport.send(msg);
	          
	         System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
		
	}

	public void sendMembershipInvoice(Users users, MembershipType newMembershipType){//, Corporate corporate) {
		
		//To send to the user.
		String to = users.getEmail();//change accordingly

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });

	      ByteArrayOutputStream outputStream = null;
	      
	      try {
	    	  
	    	  // Send Email to planet clubs Team.
	         // Create a default MimeMessage object.
	       
	    	  Message msg = new MimeMessage(session);
	          msg.setFrom(new InternetAddress(from));

	          // Set To: header field of the header.
	          msg.setRecipients(Message.RecipientType.TO,
	          InternetAddress.parse(to));

	          // Set Subject: header field
	          msg.setSubject("PlanetClubs : Membership Update");
	          
	         // msg.setRecipients(Message.RecipientType.TO, email);
	          MimeBodyPart body = new MimeBodyPart();
	          MimeMultipart multipart = new MimeMultipart("related");
	          
	          String htmlMessage = "<html><img src='http://planetclubs-geeksbench.rhcloud.com/images/oie_transparent.png' ><br>";
	          
	          String membershipType = newMembershipType.getMembershipType();
	          int noOfEnrolments = newMembershipType.getChunks();
	          
	          int totalMembershipAmount = newMembershipType.getMembershipAmount() + newMembershipType.getCorporateAmount();
	          htmlMessage += "Hi "+users.getFirstName()+",<br><br>";
	          htmlMessage += "Thank you for buying "+newMembershipType.getMembershipType().toUpperCase()+ " membership with Planetclubs.";
	          htmlMessage += "<br><br> <b>Membership Type Purchased: </b>"+membershipType;
	          htmlMessage += "<br> <b>Membership Amount: </b>Rs. "+totalMembershipAmount;
	          htmlMessage += "<br> <b>Total Enrollments: </b>"+noOfEnrolments;
	          
	          htmlMessage += "<br> <br>Go ahead and enroll into the clubs from the several options provided.";
	          
	          htmlMessage += "<br> <br>This is an autogenerated e-mail.Please do not reply to this e-mail. ";
	          
	          htmlMessage += "<br> <br>Thanks,";
	          htmlMessage += "<br>PlanetClubs Team";		          
	          
	          htmlMessage += "</html>";
	          body.setText(htmlMessage, "US-ASCII", "html");
	        //now write the PDF content to the output stream
	            outputStream = new ByteArrayOutputStream();
	            writeUserInvoice(outputStream, newMembershipType, users);
	            byte[] bytes = outputStream.toByteArray();
	             
	            //construct the pdf body part
	            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
	            MimeBodyPart pdfBodyPart = new MimeBodyPart();
	            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
	            pdfBodyPart.setFileName("Invoice.pdf");
	          //messageBodyPart.setContent(htmlMessage, "text/html");
	          multipart.addBodyPart(body);
	          multipart.addBodyPart(pdfBodyPart);
	          msg.setSentDate(new Date()); 
	          msg.setContent(multipart);
	        //  msg.setText(htmlMessage);
	         
	          Transport.send(msg);
	         System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	      /*if(corporate != null){

	    	  
	      	//To send to the corporate.
			String toCorporate = corporate.getContactEmail();//change accordingly

		      try {
		    	  
		    	  // Send Email to planet clubs Team.
		         // Create a default MimeMessage object.
		       
		    	  Message msg = new MimeMessage(session);
		          msg.setFrom(new InternetAddress(from));

		          // Set To: header field of the header.
		          msg.setRecipients(Message.RecipientType.TO,
		          InternetAddress.parse(toCorporate));

		          // Set Subject: header field
		          msg.setSubject("PlanetClubs : Membership Purchase");
		          
		         // msg.setRecipients(Message.RecipientType.TO, email);
		          MimeBodyPart body = new MimeBodyPart();
		          MimeMultipart multipart = new MimeMultipart("related");
		          
		          String htmlMessage = "<html><img src='http://planetclubs-geeksbench.rhcloud.com/images/oie_transparent.png' ><br>";
		          
		          String membershipType = newMembershipType.getMembershipType();
		          int noOfEnrolments = newMembershipType.getChunks();
		          
		          int totalMembershipAmount = newMembershipType.getMembershipAmount() + newMembershipType.getCorporateAmount();
		          htmlMessage += "Hi "+corporate.getName()+",<br><br>";
		          htmlMessage +=  users.getFirstName()+ " "+ users.getLastName()+ " purchased "+newMembershipType.getMembershipType().toUpperCase()+ " membership with Planetclubs.";
		          htmlMessage += "<br><br> <b>Details below: </b>";
		          htmlMessage += "<br><br> <b>Email: </b>"+newMembershipType.getCorporateEmail();
		          htmlMessage += "<br><br> <b>Membership Type Purchased: </b>"+membershipType;
		          htmlMessage += "<br> <b>Membership Amount: </b>Rs. "+totalMembershipAmount;
		          htmlMessage += "<br> <b>Corporate Amount: </b>Rs. "+newMembershipType.getCorporateAmount();
		          htmlMessage += "<br> <b>Total Enrollments: </b>"+noOfEnrolments;
		          
		          htmlMessage += "<br> <br>This is an autogenerated e-mail.Please do not reply to this e-mail. ";
		          
		          htmlMessage += "<br> <br>Thanks,";
		          htmlMessage += "<br>PlanetClubs Team";		          
		          
		          htmlMessage += "</html>";
		          body.setText(htmlMessage, "US-ASCII", "html");
		        //now write the PDF content to the output stream
		            outputStream = new ByteArrayOutputStream();
		            writeCorporateInvoice(outputStream, membershipType, totalMembershipAmount, noOfEnrolments,newMembershipType);
		            byte[] bytes = outputStream.toByteArray();
		             
		            //construct the pdf body part
		            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
		            MimeBodyPart pdfBodyPart = new MimeBodyPart();
		            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
		            pdfBodyPart.setFileName("Invoice.pdf");
		          //messageBodyPart.setContent(htmlMessage, "text/html");
		          multipart.addBodyPart(body);
		          multipart.addBodyPart(pdfBodyPart);
		          msg.setSentDate(new Date()); 
		          msg.setContent(multipart);
		        //  msg.setText(htmlMessage);
		         
		          Transport.send(msg);
		         System.out.println("Sent message successfully....");

		      } catch (MessagingException e) {
		            throw new RuntimeException(e);
		      } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	  
	      }*/
  
	      
	}

    /**
     * Writes the content of a PDF file (using iText API)
     * to the {@link OutputStream}.
     * @param outputStream {@link OutputStream}.
     * @throws Exception
     */
    public void writeUserInvoice(OutputStream outputStream, MembershipType newMembershipType, Users users) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
         
        document.open();
         
        document.addTitle("PlanetClubs Invoice");
        document.addAuthor("Planet Clubs");
        document.addCreator("Planet Clubs");
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        Image img = Image.getInstance("http://planetclubs-geeksbench.rhcloud.com/images/oie_transparent.png");
        img.setAlignment(Image.ALIGN_CENTER);
        document.add(img);
        document.add( Chunk.NEWLINE );
        Paragraph title = new Paragraph(new Phrase("Planet Clubs Membership Invoice", boldFont)); 
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add( Chunk.NEWLINE );
        /*document.add(new Paragraph("Plenet Clubs Membership Invoice"));
        document.add(new Paragraph("Membership Type Purchased:"+membershipType));
        document.add(new Paragraph("Membership Amount: Rs. "+totalMembershipAmount));
        document.add(new Paragraph("Total Enrollments: "+noOfEnrolments));*/
     // a table with three columns
        PdfPTable table = new PdfPTable(2);
        // the cell object
        //PdfPCell cell;
        // we add a cell with colspan 3
        /*cell = new PdfPCell(new Phrase("Cell with colspan 3"));
        cell.setColspan(3);
        table.addCell(cell);*/
        // now we add a cell with rowspan 2
        /*cell = new PdfPCell(new Phrase("2ell with rowspan 2"));
        cell.setRowspan(2);
        table.addCell(cell);*/
        // we add the four remaining cells with addCell()
        table.addCell(new Phrase("Name",boldFont));
        table.addCell(new Phrase("Description",boldFont));
        table.addCell("Membership Number");
        table.addCell(newMembershipType.getMembershipNo());
        table.addCell("Name");
        table.addCell(users.getFirstName() + ' ' + users.getLastName());
        table.addCell("Membership Purchased");
        table.addCell(newMembershipType.getMembershipType());
        table.addCell("Total Enrollments Purchased");
        table.addCell(Integer.toString(newMembershipType.getChunks()) );
        table.addCell("Purchase Date");
        table.addCell(newMembershipType.getStartDate().toString() );
        table.addCell("Membership Amount");
        table.addCell("Rs. "+ Integer.toString(newMembershipType.getMembershipAmount()) );
/*        Paragraph paragraph = new Paragraph();
        paragraph.add(new Chunk("Thanks"));
        document.add( Chunk.NEWLINE );
        paragraph.add(new Chunk("Thanks"));
        document.add(paragraph);*/
        document.add(table); 
        document.close();
    }
    
    /**
     * Writes the content of a PDF file (using iText API)
     * to the {@link OutputStream}.
     * @param outputStream {@link OutputStream}.
     * @throws Exception
     */
   /* public void writeCorporateInvoice(OutputStream outputStream, String membershipType, int totalMembershipAmount, int noOfEnrolments, MembershipType membership) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
         
        document.open();
         
        document.addTitle("PlanetClubs Invoice");
        document.addAuthor("Planet Clubs");
        document.addCreator("Planet Clubs");
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        Image img = Image.getInstance("http://planetclubs-geeksbench.rhcloud.com/images/oie_transparent.png");
        img.setAlignment(Image.ALIGN_CENTER);
        document.add(img);
        document.add( Chunk.NEWLINE );
        Paragraph title = new Paragraph(new Phrase("Plenet Clubs Membership Invoice", boldFont)); 
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add( Chunk.NEWLINE );
        document.add(new Paragraph("Plenet Clubs Membership Invoice"));
        document.add(new Paragraph("Membership Type Purchased:"+membershipType));
        document.add(new Paragraph("Membership Amount: Rs. "+totalMembershipAmount));
        document.add(new Paragraph("Total Enrollments: "+noOfEnrolments));
     // a table with three columns
        PdfPTable table = new PdfPTable(2);
        // the cell object
        //PdfPCell cell;
        // we add a cell with colspan 3
        cell = new PdfPCell(new Phrase("Cell with colspan 3"));
        cell.setColspan(3);
        table.addCell(cell);
        // now we add a cell with rowspan 2
        cell = new PdfPCell(new Phrase("2ell with rowspan 2"));
        cell.setRowspan(2);
        table.addCell(cell);
        // we add the four remaining cells with addCell()
        table.addCell("Name");
        table.addCell("Details");
        table.addCell("Email:");
        table.addCell(membership.getCorporateEmail());
        table.addCell("Membership Purchased:");
        table.addCell(membershipType);
        table.addCell("Membership Amount:");
        table.addCell("Rs. "+ Integer.toString(totalMembershipAmount) );
        table.addCell("Corporate Amount:");
        table.addCell("Rs. "+ Integer.toString(membership.getCorporateAmount()) );
        table.addCell("Total Enrollments Purchased:");
        table.addCell(Integer.toString(noOfEnrolments) );
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Chunk("Thanks"));
        document.add( Chunk.NEWLINE );
        paragraph.add(new Chunk("Thanks"));
        document.add(paragraph);
        document.add(table);
        document.close();
    }*/
	
	public void sendCorporateEmailToUser(String email, String name, String token) {
		String to = email;//change accordingly

	      // Sender's email ID needs to be mentioned
	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });

	      try {
	         // Create a default MimeMessage object.
	       
	    	  Message msg = new MimeMessage(session);
	          msg.setFrom(new InternetAddress(from));

	          // Set To: header field of the header.
	          msg.setRecipients(Message.RecipientType.TO,
	          InternetAddress.parse(to));

	          // Set Subject: header field
	          msg.setSubject("Verify Planetclubs Membership enrollment");
	          
	         // msg.setRecipients(Message.RecipientType.TO, email);
	          MimeBodyPart body = new MimeBodyPart();
	          MimeMultipart multipart = new MimeMultipart("related");
	          //BodyPart messageBodyPart = new MimeBodyPart();
	          String link = Constants.WEB_LINK+"/rest/emailservice/verifycorpemail/"+token;

	          String htmlMessage = "<html><img src='http://planetclubs-geeksbench.rhcloud.com/images/oie_transparent.png' ><br>";
	           
	          htmlMessage += "Hi, <br>";
	          htmlMessage += name + "has enrolled for membership in Planetclubs.<br>";
	          htmlMessage += "Please verify if "+name+" is authorised to enroll for this membership.<br>";
	          htmlMessage += "<br>Please click this <a href="+ link +">link<a> to approve membership.<br>";
	          htmlMessage += "<br><br>Thanks,";
	          htmlMessage += "<br>PlanetClubs Team";
	          htmlMessage += "</html>";
	          body.setText(htmlMessage, "US-ASCII", "html");
	          multipart.addBodyPart(body);
	          msg.setSentDate(new Date()); 
	          msg.setContent(multipart);
	         
	          Transport.send(msg);

	    	 
	         System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }

	}

	public void enrollcorporate(String name, String city, String selectedEmployees, String contact, String email,
			String number) {
			// Recipient's email ID needs to be mentioned.
	      String to = "planetclubs@googlegroups.com";//change accordingly

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });

	      try {
	    	  
	    	  // Send Email to planet clubs Team.
	         // Create a default MimeMessage object.
	       
	    	  Message msg = new MimeMessage(session);
	          msg.setFrom(new InternetAddress(from));

	          // Set To: header field of the header.
	          msg.setRecipients(Message.RecipientType.TO,
	          InternetAddress.parse(to));

	          // Set Subject: header field
	          msg.setSubject("PlanetClubs : Corporate onboard Request Recorded");
	          
	         // msg.setRecipients(Message.RecipientType.TO, email);
	          MimeBodyPart body = new MimeBodyPart();
	          MimeMultipart multipart = new MimeMultipart("related");
	          
	          String htmlMessage = "<html><img src='http://planetclubs-geeksbench.rhcloud.com/images/oie_transparent.png' ><br>";
	          
	          htmlMessage += "Hi Team, <br><br>";
	          htmlMessage += name + " has raised request to onboard club." ;
	          htmlMessage += "<br> <br>Details:";
	          htmlMessage += "<br>Name : "+ name;
	          htmlMessage += "<br>City : "+ city;
	          htmlMessage += "<br>Number Of Employees : "+ selectedEmployees;
	          htmlMessage += "<br>Contact Person : "+ contact;
	          htmlMessage += "<br>Email : "+ email;
	          htmlMessage += "<br>Telephone Number : "+ number;
	          htmlMessage += "<br> <br>Cheers!";
	          htmlMessage += "</html>";
	          body.setText(htmlMessage, "US-ASCII", "html");
	          //messageBodyPart.setContent(htmlMessage, "text/html");
	          multipart.addBodyPart(body);
	          msg.setSentDate(new Date()); 
	          msg.setContent(multipart);
	        //  msg.setText(htmlMessage);
	         
	          Transport.send(msg);

	        if(email != "" && email.indexOf('@')> -1){
	        	
	        	// Send Email to user.
		         // Create a default MimeMessage object.
		       
		    	  Message userMsg = new MimeMessage(session);
		    	  userMsg.setFrom(new InternetAddress(from));
	
		          // Set To: header field of the header.
		    	  userMsg.setRecipients(Message.RecipientType.TO,
		          InternetAddress.parse(email));
	
		          // Set Subject: header field
		    	  userMsg.setSubject("PlanetClubs : Thanks " + name +" for Corporate partnership Request");
		          
		         // msg.setRecipients(Message.RecipientType.TO, email);
		          MimeBodyPart userBody = new MimeBodyPart();
		          MimeMultipart usermultipart = new MimeMultipart("related");
		          
		          String userhtmlMessage = "<html><img src='http://planetclubs-geeksbench.rhcloud.com/images/oie_transparent.png' ><br>";
		          		          
		          userhtmlMessage += "Hi "+name+",<br><br>";
		          userhtmlMessage += "Thanks for Corporate partnership Request. Our Team will get back to you in 48 hours.";
		          userhtmlMessage += "<br> <br>Thanks,";
		          userhtmlMessage += "<br>PlanetClubs Team";		          
		          userhtmlMessage += "</html>";
		          userBody.setText(userhtmlMessage, "US-ASCII", "html");
		          //messageBodyPart.setContent(htmlMessage, "text/html");
		          usermultipart.addBodyPart(userBody);
		          userMsg.setSentDate(new Date()); 
		          userMsg.setContent(usermultipart);
		        //  msg.setText(htmlMessage);
		         
		          Transport.send(userMsg);
	        	
	        }
	          
	          
	         System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
		
	}



}