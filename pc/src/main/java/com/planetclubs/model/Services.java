package com.planetclubs.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "services")
public class Services implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="serviceId")
	private int serviceId;

	@Column(name="clubId")
	private int clubId;
	
	@Column(name="serviceType")
	private String serviceType;
	
	@Column(name="payAmount")
	private int payAmount;
	
	@Column(name = "membership")
	private int membership;
	
	@Column(name = "walkinAllowed")
	private boolean walkinAllowed;
	
	@Column(name = "advancedBooking")
	private boolean advancedBooking;
	

	@Column(name = "enrollmentsRequired")
	private int enrollmentsRequired;
	
	
	public int getClubId() {
		return clubId;
	}


	public void setClubId(int clubId) {
		this.clubId = clubId;
	}


	public String getServiceType() {
		return serviceType;
	}


	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	
	
	public int getServiceId() {
		return serviceId;
	}


	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	
	


	public int getPayAmount() {
		return payAmount;
	}


	public void setPayAmount(int payAmount) {
		this.payAmount = payAmount;
	}


	public int getMembership() {
		return membership;
	}


	public void setMembership(int membership) {
		this.membership = membership;
	}


	public boolean isAdvancedBooking() {
		return advancedBooking;
	}


	public void setAdvancedBooking(boolean advancedBooking) {
		this.advancedBooking = advancedBooking;
	}

	

	public int getEnrollmentsRequired() {
		return enrollmentsRequired;
	}


	public void setEnrollmentsRequired(int enrollmentsRequired) {
		this.enrollmentsRequired = enrollmentsRequired;
	}

	

	public boolean isWalkinAllowed() {
		return walkinAllowed;
	}


	public void setWalkinAllowed(boolean walkinAllowed) {
		this.walkinAllowed = walkinAllowed;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
