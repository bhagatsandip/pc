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
    "user",
    "appName",
    "page",
    "action",
    "service",
    "request",
    "responseStatus",
    "reason"
})
@XmlRootElement(name = "LogsType")
public class LogsType {
	
	private int id;
	
	@XmlElement(required = true)
	private String user;
	
	@XmlElement(required = false)
	private String appName;

	@XmlElement(required = false)
	private String page;
	
	@XmlElement(required = false)
	private String action;
	
	@XmlElement(required = false)
	private String service;
	
	@XmlElement(required = false)
	private String request;
	
	@XmlElement(required = false)
	private String responseStatus;
	
	@XmlElement(required = false)
	private String reason;
	
	@XmlElement(required = true)
	private Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	


}
