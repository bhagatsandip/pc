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
 "serviceDetailsType"
})
@XmlRootElement(name = "ServiceDetailsListType")
public class ServiceDetailsListType {

 @XmlElement(name = "serviceDetailsType")
 protected List<ServiceDetailsType> serviceDetailsType;

 public List<ServiceDetailsType> getServiceDetailsType() {
     if (serviceDetailsType == null) {
    	 serviceDetailsType = new ArrayList<ServiceDetailsType>();
     }
     return this.serviceDetailsType;
 }

}

