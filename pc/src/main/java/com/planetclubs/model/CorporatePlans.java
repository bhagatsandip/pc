package com.planetclubs.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "corporateplans")
public class CorporatePlans implements Serializable{


	private static long serialVersionUID = 1L;


	@Id
    @GeneratedValue
    @Column(name="id")
    protected int id;
	
	@Column(name="corporateId")
    protected int corporateId;
	
	@Column(name="amount")
    protected int amount;
	
	@Column(name="noOfEmployees")
    protected int noOfEmployees;
	
	@Column(name="bookedUsages")
    protected int bookedUsages;
	
    @Column(name = "validFrom")
    protected Date validFrom;

    @Column(name = "validTo")
    protected Date validTo;

    @Column(name = "isValid")
    protected boolean isValid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCorporateId() {
		return corporateId;
	}

	public void setCorporateId(int corporateId) {
		this.corporateId = corporateId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getNoOfEmployees() {
		return noOfEmployees;
	}

	public void setNoOfEmployees(int noOfEmployees) {
		this.noOfEmployees = noOfEmployees;
	}

	public int getBookedUsages() {
		return bookedUsages;
	}

	public void setBookedUsages(int bookedUsages) {
		this.bookedUsages = bookedUsages;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}	
}
