package com.planetclubs.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@Entity
@Table(name = "usages")
public class Usage implements Serializable{

	
private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue
    @Column(name="id")
	private int id;
	
	@Column(name = "pcMembershipId")
	private int pcMembershipId;
	
	 @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "startDateTime")
	private Date startDateTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "endDateTime")
	private Date endDateTime;
	
	@Column(name = "details")
	private String details;
	
	@Column(name = "isApproved")
	private String isApproved;
	
	@Column(name = "userId")
	private int userId;
	
	@Column(name = "clubId")
	private int clubId;
	
	@Column(name="serviceType")
	private String serviceType;
	
	@Column(name = "serviceUnitCost")
	private int serviceUnitCost;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPcMembershipId() {
		return pcMembershipId;
	}

	public void setPcMembershipId(int pcMembershipId) {
		this.pcMembershipId = pcMembershipId;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}

	public int getClubId() {
		return clubId;
	}

	public void setClubId(int clubId) {
		this.clubId = clubId;
	}
	
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public int getServiceUnitCost() {
		return serviceUnitCost;
	}

	public void setServiceUnitCost(int serviceUnitCost) {
		this.serviceUnitCost = serviceUnitCost;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}