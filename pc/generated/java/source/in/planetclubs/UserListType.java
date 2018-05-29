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
 "userType"
})
@XmlRootElement(name = "UserListType")
public class UserListType {

 @XmlElement(name = "userType")
 protected List<UserType> userType;

 public List<UserType> getUserType() {
     if (userType == null) {
    	 userType = new ArrayList<UserType>();
     }
     return this.userType;
 }

}

