package com.planetclubs.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "membership")
public class Membership implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue
    @Column(name="membershipId")
	private int membershipId;
	
	@Column(name="membershipNo")
	private String membershipNo;
	
	@Column(name = "startDate")
	private Date startDate;
	
	@Column(name = "endDate")
	private Date endDate;
	
	@Column(name = "updateDate")
	private Date updateDate;
	
	@Column(name = "totalAddonPoints")
	private int totalAddonPoints;
	
	@Column(name = "userId")
	private int userId;
	
	@Column(name = "membershipType")
	private String membershipType;

	@Column(name = "chunks")
	private int chunks;
	
	@Column(name = "active")
	private int active;

	@Column(name = "corporateId")
	private int corporateId;
	
	@Column(name = "membershipAmount")
	private int membershipAmount;
	
	@Column(name = "corporateAmount")
	private int corporateAmount;

	@Column(name = "corporateEmail")
	private String corporateEmail;
	
	@Column(name = "couponCode")
	private String couponCode;

	public int getMembershipId() {
		return membershipId;
	}

	public void setMembershipId(int membershipId) {
		this.membershipId = membershipId;
	}

	public String getMembershipNo() {
		return membershipNo;
	}

	public void setMembershipNo(String membershipNo) {
		this.membershipNo = membershipNo;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(String membershipType) {
		this.membershipType = membershipType;
	}

	public int getTotalAddonPoints() {
		return totalAddonPoints;
	}

	public void setTotalAddonPoints(int totalAddonPoints) {
		this.totalAddonPoints = totalAddonPoints;
	}

	public int getChunks() {
		return chunks;
	}

	public void setChunks(int chunks) {
		this.chunks = chunks;
	}

	public int isActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
	
	public int getCorporateId() {
		return corporateId;
	}

	public void setCorporateId(int corporateId) {
		this.corporateId = corporateId;
	}

	public int getMembershipAmount() {
		return membershipAmount;
	}

	public void setMembershipAmount(int membershipAmount) {
		this.membershipAmount = membershipAmount;
	}

	public int getCorporateAmount() {
		return corporateAmount;
	}

	public void setCorporateAmount(int corporateAmount) {
		this.corporateAmount = corporateAmount;
	}

	public String getCorporateEmail() {
		return corporateEmail;
	}

	public void setCorporateEmail(String corporateEmail) {
		this.corporateEmail = corporateEmail;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
