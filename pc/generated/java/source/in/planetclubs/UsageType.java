//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.25 at 01:20:57 AM IST 
//


package in.planetclubs;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "pcMembershipId",
    "startDateTime",
    "endDateTime",
    "details",
    "isApproved",
    "userId",
    "clubId",
    "serviceType",
    "serviceUnitCost"
})
@XmlRootElement(name = "UsageType")
public class UsageType {

    protected int id;
    @XmlElement(required = true)
    protected int pcMembershipId;
    @XmlElement(required = true)
    protected Date startDateTime;
    @XmlElement(required = true)
    protected Date endDateTime;
    @XmlElement(required = true)
    protected String details;
    @XmlElement(required = true)
    protected String isApproved;
    @XmlElement(required = true)
    protected int userId;
    @XmlElement(required = true)
    protected int clubId;
    @XmlElement(required = true)
    protected String serviceType;
    @XmlElement(required = true)
    protected int serviceUnitCost;
    @XmlElement(required = true)
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	public int getPcMembershipId() {
		return pcMembershipId;
	}
	public void setPcMembershipId(int pcMembershipId) {
		this.pcMembershipId = pcMembershipId;
	}
	public Date getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}
	public Date getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getClubId() {
		return clubId;
	}
	public void setClubId(int clubId) {
		this.clubId = clubId;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public int getServiceUnitCost() {
		return serviceUnitCost;
	}
	public void setServiceUnitCost(int serviceUnitCost) {
		this.serviceUnitCost = serviceUnitCost;
	}
    

    
}
