package com.planetclubs.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "poolbookingusages")
public class PoolBookingUsages implements Serializable{

	private static long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    @Column(name="id")
    protected int id;
	
	@Column(name="clubPoolBookingId")
	protected int clubPoolBookingId;	
	
	@Column(name="usageId")
	protected int usageId;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClubPoolBookingId() {
		return clubPoolBookingId;
	}

	public void setClubPoolBookingId(int clubPoolBookingId) {
		this.clubPoolBookingId = clubPoolBookingId;
	}

	public int getUsageId() {
		return usageId;
	}

	public void setUsageId(int usageId) {
		this.usageId = usageId;
	}
	
}
