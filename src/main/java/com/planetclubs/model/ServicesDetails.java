package com.planetclubs.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@Entity
@Table(name = "servicedetails")
public class ServicesDetails implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="id")
	private int id;

	@Column(name="serviceId")
	private int serviceId;
	
	@Column(name="serviceUnitCost")
	private int serviceUnitCost;
	
	@Column(name = "serviceDuration")
	private float serviceDuration;

	@Column(name = "startTime")
	private Date startTime;
	
	@Column(name = "endTime")
	private Date endTime;
	
	@Column(name="weekend")
	private boolean weekend;
	 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public int getServiceUnitCost() {
		return serviceUnitCost;
	}

	public void setServiceUnitCost(int serviceUnitCost) {
		this.serviceUnitCost = serviceUnitCost;
	}

	public float getServiceDuration() {
		return serviceDuration;
	}

	public void setServiceDuration(float serviceDuration) {
		this.serviceDuration = serviceDuration;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public boolean isWeekend() {
		return weekend;
	}

	public void setWeekend(boolean weekend) {
		this.weekend = weekend;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
