package in.planetclubs;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
 "serviceType"
})
@XmlRootElement(name = "ServiceListType")
public class ServiceListType {

	 @XmlElement(name = "serviceType")
	 protected List<ServiceType> serviceType;
	
	 public List<ServiceType> getServiceType() {
	     if (serviceType == null) {
	    	 serviceType = new ArrayList<ServiceType>();
	     }
	     return this.serviceType;
	 }

}

