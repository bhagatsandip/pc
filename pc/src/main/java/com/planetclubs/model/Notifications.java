package com.planetclubs.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notifications")
public class Notifications implements Serializable{

private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="userId")
	private int userId;
	
	@Column(name = "clubId")
	private int clubId;
	
	@Column(name = "notificationText")
	private String notificationText;
	
	@Column(name = "isRead")
	private Boolean isRead;
	
	@Column(name = "date")
	private Date date;

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getClubId() {
		return clubId;
	}


	public void setClubId(int clubId) {
		this.clubId = clubId;
	}


	public String getNotificationText() {
		return notificationText;
	}


	public void setNotificationText(String notificationText) {
		this.notificationText = notificationText;
	}


	public Boolean getIsRead() {
		return isRead;
	}


	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
