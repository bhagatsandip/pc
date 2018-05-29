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
    "clubId",
    "fromDate",
    "toDate",
    "banner",
    "location",
    "live"
})
@XmlRootElement(name = "ClubPromotionsType")
public class ClubPromotionsType {

	@XmlElement(required = true)
	protected int id;
    @XmlElement(required = true)
    protected int clubId;
    @XmlElement(required = true)
    protected Date fromDate;
    @XmlElement(required = false)
    protected Date toDate;
    @XmlElement(required = true)
    protected String banner;
    @XmlElement(required = true)
    protected String location;
    @XmlElement(required = false)
    protected boolean live;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClubId() {
		return clubId;
	}
	public void setClubId(int clubId) {
		this.clubId = clubId;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
    
    
	    
}
