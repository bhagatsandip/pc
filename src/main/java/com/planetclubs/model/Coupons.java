package com.planetclubs.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coupons")
public class Coupons implements Serializable{

private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    @Column(name="id")
	private int id;
	
	@Column(name="couponCode")
	private String couponCode;
	
	@Column(name="couponDesc")
	private String couponDesc;
	
	@Column(name = "discount")
	private int discount;

	@Column(name = "discType")
	private String discType;
	
	@Column(name = "fupBonus")
	private int fupBonus;
	
	@Column(name = "validFrom")
	private Date validFrom;
	
	@Column(name = "validTo")
	private Date validTo;
	
	@Column(name = "isLive")
	private boolean isLive;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getCouponDesc() {
		return couponDesc;
	}

	public void setCouponDesc(String couponDesc) {
		this.couponDesc = couponDesc;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getDiscType() {
		return discType;
	}

	public void setDiscType(String discType) {
		this.discType = discType;
	}

	public int getFupBonus() {
		return fupBonus;
	}

	public void setFupBonus(int fupBonus) {
		this.fupBonus = fupBonus;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
