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
@Table(name = "payments")
public class Payments implements Serializable{

	
private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue
    @Column(name="id")
	private int id;
	
	@Column(name = "clubId")
	private int clubId;
	
	@Column(name="amount")
	private int amount;

	@Column(name = "paymentDate")
	private Date paymentDate;

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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}