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
 "ratingsType"
})
@XmlRootElement(name = "RatingsListType")
public class RatingsListType {

 @XmlElement(name = "ratingType")
 protected List<RatingType> ratingType;

 public List<RatingType> getServiceType() {
     if (ratingType == null) {
    	 ratingType = new ArrayList<RatingType>();
     }
     return this.ratingType;
 }

}

