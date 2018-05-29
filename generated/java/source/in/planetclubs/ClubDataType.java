package in.planetclubs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
 "clubType",
 "usageListType",
 "userListType"
})
@XmlRootElement(name = "ClubDataType")
public class ClubDataType {

	 @XmlElement(name = "clubType")
	 protected ClubType clubType;
	
	 @XmlElement(name = "usageListType")
	 protected UsageListType usageListType;
	 
	 @XmlElement(name = "userListType")
	 protected UserListType userListType;
	 
	 @XmlElement(name = "servicesListType")
	 protected ServiceListType servicesListType;
	
	public ClubType getClubType() {
		return clubType;
	}
	
	public void setClubType(ClubType clubType) {
		this.clubType = clubType;
	}
	
	public UsageListType getUsageListType() {
		return usageListType;
	}
	
	public void setUsageListType(UsageListType usageListType) {
		this.usageListType = usageListType;
	}
	
	public UserListType getUserListType() {
		return userListType;
	}
	
	public void setUserListType(UserListType userListType) {
		this.userListType = userListType;
	}
	
	public ServiceListType getServicesListType() {
		return servicesListType;
	}
	
	public void setServicesListType(ServiceListType servicesListType) {
		this.servicesListType = servicesListType;
	}	
}

