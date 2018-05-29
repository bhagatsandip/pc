package com.planetclubs.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users implements Serializable{

private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue
	@Column(name="USERID")
	private int userId;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name = "FIRSTNAME")
	private String firstName;
	
	@Column(name = "LASTNAME")
	private String lastName;
	
	@Column(name = "CONTACTNO")
	private String contactNo;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "USERTYPE")
	private String userType;
	
	@Column(name = "VERIFIED")
	private boolean verified;
	
	@Column(name = "COMPVERIFIED")
	private boolean compVerified;
	
	@Column(name = "CORPORATEID")
	private int corporateId;
	
	@Column(name = "REFERALCODE")
	private String referalCode;

	@Column(name = "TOKEN")
	private String token;
	
	@Column(name = "RESETPASSWORD")
	private boolean resetPassword;

	@Column(name = "RESETPASSWORDTOKEN")
	private String resetPasswordToken;
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	
	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}	

	public boolean isResetPassword() {
		return resetPassword;
	}

	public void setResetPassword(boolean resetPassword) {
		this.resetPassword = resetPassword;
	}

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

	public int getCorporateId() {
		return corporateId;
	}

	public void setCorporateId(int corporateId) {
		this.corporateId = corporateId;
	}

	
	public String getReferalCode() {
		return referalCode;
	}

	public void setReferalCode(String referalCode) {
		this.referalCode = referalCode;
	}

	
	public boolean isCompVerified() {
		return compVerified;
	}

	public void setCompVerified(boolean compVerified) {
		this.compVerified = compVerified;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
