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
 "notificationsType"
})
@XmlRootElement(name = "NotificationsListType")
public class NotificationsListType {

 @XmlElement(name = "notificationsType")
 protected List<NotificationsType> notificationsType;

 public List<NotificationsType> getNotificationsType() {
     if (notificationsType == null) {
    	 notificationsType = new ArrayList<NotificationsType>();
     }
     return this.notificationsType;
 }

}

