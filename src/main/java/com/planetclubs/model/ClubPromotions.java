package com.planetclubs.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clubpromotions")
public class ClubPromotions implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue
    @Column(name="id")
	private int id;
	
	@Column(name="clubId")
	private int clubId;
	
	@Column(name="fromDate")
	private Date fromDate;
	
	@Column(name = "toDate")
	private Date toDate;

	@Column(name = "banner")
	private String banner;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "live")
	private boolean live;

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

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}


	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
