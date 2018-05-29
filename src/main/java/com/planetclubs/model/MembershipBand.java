package com.planetclubs.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "membershiptypes")
public class MembershipBand implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue
    @Column(name="id")
	private int id;
	
	@Column(name = "membershipType")
	private String membershipType;

	@Column(name = "monthly")
	private int monthly;
	
	@Column(name = "quarterly")
	private int quarterly;
	
	@Column(name = "halfYearly")
	private int halfYearly;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(String membershipType) {
		this.membershipType = membershipType;
	}

	public int getMonthly() {
		return monthly;
	}

	public void setMonthly(int monthly) {
		this.monthly = monthly;
	}

	public int getQuarterly() {
		return quarterly;
	}

	public void setQuarterly(int quarterly) {
		this.quarterly = quarterly;
	}

	public int getHalfYearly() {
		return halfYearly;
	}

	public void setHalfYearly(int halfYearly) {
		this.halfYearly = halfYearly;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
