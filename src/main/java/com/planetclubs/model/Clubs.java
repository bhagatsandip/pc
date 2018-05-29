package com.planetclubs.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clubs")
public class Clubs implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue
    @Column(name="id")
	private int id;
	
	@Column(name="clubId")
	private String clubId;
	
	@Column(name="userId")
	private int userId;
	
	@Column(name = "clubName")
	private String clubName;

	@Column(name = "clubType")
	private String clubType;
	
	@Column(name = "clubAddress")
	private String clubAddress;
	
	@Column(name = "clubLocation")
	private String clubLocation;
	
	@Column(name = "clubCity")
	private String clubCity;
	
	@Column(name = "clubCountry")
	private String clubCountry;
	
	@Column(name = "contactNo1")
	private String contactNo1;
	
	@Column(name = "contactNo2")
	private String contactNo2;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "latitude")
	private float latitude;
	
	@Column(name = "longitude")
	private float longitude;
	
	@Column(name = "aboutUs")
	private String aboutUs;
	
	@Column(name = "membershipType")
	private String membershipType;
	
	@Column(name = "facilities")
	private String facilities;	
	
	@Column(name = "notes")
	private String notes;	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getClubId() {
		return clubId;
	}


	public void setClubId(String clubId) {
		this.clubId = clubId;
	}

	

	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getClubName() {
		return clubName;
	}


	public void setClubName(String clubName) {
		this.clubName = clubName;
	}


	public String getClubAddress() {
		return clubAddress;
	}


	public void setClubAddress(String clubAddress) {
		this.clubAddress = clubAddress;
	}

	

	public String getClubLocation() {
		return clubLocation;
	}


	public void setClubLocation(String clubLocation) {
		this.clubLocation = clubLocation;
	}


	public String getClubCity() {
		return clubCity;
	}


	public void setClubCity(String clubCity) {
		this.clubCity = clubCity;
	}


	public String getClubCountry() {
		return clubCountry;
	}


	public void setClubCountry(String clubCountry) {
		this.clubCountry = clubCountry;
	}


	public String getContactNo1() {
		return contactNo1;
	}


	public void setContactNo1(String contactNo1) {
		this.contactNo1 = contactNo1;
	}


	public String getContactNo2() {
		return contactNo2;
	}


	public void setContactNo2(String contactNo2) {
		this.contactNo2 = contactNo2;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public float getLatitude() {
		return latitude;
	}


	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}


	public float getLongitude() {
		return longitude;
	}


	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}


	public String getClubType() {
		return clubType;
	}


	public void setClubType(String clubType) {
		this.clubType = clubType;
	}


	public String getAboutUs() {
		return aboutUs;
	}


	public void setAboutUs(String aboutUs) {
		this.aboutUs = aboutUs;
	}

	
	
	public String getMembershipType() {
		return membershipType;
	}


	public void setMembershipType(String membershipType) {
		this.membershipType = membershipType;
	}


	public String getFacilities() {
		return facilities;
	}


	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}

	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
