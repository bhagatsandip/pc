package com.planetclubs.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clubspoolbooking")
public class ClubsPoolBooking implements Serializable{

	/**
	 * 
	 */
	private static long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    @Column(name="id")
    protected int id;
	
	@Column(name = "corporatePlanId")
    protected int corporatePlanId;
	
	@Column(name = "bookedUsagesCount")
    protected int bookedUsagesCount;

	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCorporatePlanId() {
		return corporatePlanId;
	}

	public void setCorporatePlanId(int corporatePlanId) {
		this.corporatePlanId = corporatePlanId;
	}

	public int getBookedUsagesCount() {
		return bookedUsagesCount;
	}

	public void setBookedUsagesCount(int bookedUsagesCount) {
		this.bookedUsagesCount = bookedUsagesCount;
	}
	
}
