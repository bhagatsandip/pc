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

import org.hibernate.annotations.IndexColumn;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@Entity
@Table(name = "pcmemberships")
public class PCMembership implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue
    @Column(name="id")
	private int id;
	
	@Column(name="membershipId")
	private int membershipId;
	
	@Column(name="clubId")
	private int clubId;
	
	@Column(name="serviceId")
	private int serviceId;
	
	@Column(name="enrollmentsUsed")
	private int enrollmentsUsed;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "startTime")
	private Date startTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "endTime")
	private Date endTime;
	
	@Column(name = "userId")
	private int userId;
	
	@Column(name = "autoRenew")
	private boolean autoRenew;
	
	@Column(name = "active")
	private boolean active;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClubId() {
		return clubId;
	}

	public void setClubId(int clubId) {
		this.clubId = clubId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getMembershipId() {
		return membershipId;
	}

	public void setMembershipId(int membershipId) {
		this.membershipId = membershipId;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public boolean isAutoRenew() {
		return autoRenew;
	}

	public void setAutoRenew(boolean autoRenew) {
		this.autoRenew = autoRenew;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public int getEnrollmentsUsed() {
		return enrollmentsUsed;
	}

	public void setEnrollmentsUsed(int enrollmentsUsed) {
		this.enrollmentsUsed = enrollmentsUsed;
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
