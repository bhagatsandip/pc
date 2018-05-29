package com.planetclubs.service;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.planetclubs.dao.LogsDAO;
import com.planetclubs.dao.MembershipDAO;
import com.planetclubs.model.Logs;

import in.planetclubs.LogsType;

@Component
@Path("/logservice")
public class LogService implements ILogService{
	
	@Autowired
	private LogsDAO logsDAO;
	
	@POST
	@Path("addlog")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public void createLog(LogsType logsType) {
	
		Logs logs = new Logs();
		
		logs.setAction(logsType.getAction());
		logs.setAppName(logsType.getAppName());
		logs.setDate(new Date());
		logs.setPage(logsType.getPage());
		logs.setReason(logsType.getReason());
		logs.setRequest(logsType.getRequest());
		logs.setResponseStatus(logsType.getResponseStatus());
		logs.setService(logsType.getService());
		logs.setUser(logsType.getUser());
		
		logsDAO.insertNewLogs(logs);
		
		
	}

}
