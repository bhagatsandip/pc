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
 "memberhsipBandType"
})
@XmlRootElement(name = "MembershipListType")
public class MembershipBandListType {

 @XmlElement(name = "membershipBandType")
 protected List<MembershipBandType> membershipBandType;

 public List<MembershipBandType> getmembershipBandType() {
     if (membershipBandType == null) {
    	 membershipBandType = new ArrayList<MembershipBandType>();
     }
     return this.membershipBandType;
 }

}

