package com.planetclubs.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clubspoolbookingmap")
public class ClubsPoolBookingMap implements Serializable{

	/**
	 * 
	 */
	private static long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    @Column(name="id")
    protected int id;
	
	@Column(name="clubsPoolBookingId")
    protected int clubsPoolBookingId;
	
	@Column(name="serviceId")
    protected int serviceId;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClubsPoolBookingId() {
		return clubsPoolBookingId;
	}

	public void setClubsPoolBookingId(int clubsPoolBookingId) {
		this.clubsPoolBookingId = clubsPoolBookingId;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}	
}
