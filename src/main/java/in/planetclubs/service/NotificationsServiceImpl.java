package com.planetclubs.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.planetclubs.dao.NotificationsDAO;
import com.planetclubs.model.Membership;
import com.planetclubs.model.Notifications;
import com.planetclubs.util.Utils;

import in.planetclubs.MembershipType;
import in.planetclubs.NotificationsListType;
import in.planetclubs.NotificationsType;

@Component
@Path("/notificationsservice")
public class NotificationsServiceImpl implements INotificationsService{

	@Autowired
	private NotificationsDAO notificationsDAO;
	
	@Autowired
	private Utils utils;
	
	@POST
	@Path("addnotification")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String createOrSaveNotifications(NotificationsType notificationType) {

		Notifications notification = new Notifications();
		notification.setClubId(notificationType.getClubId());
		Date now = utils.getCurrentTime();
		notification.setDate(now);
		notification.setIsRead(notificationType.isRead());
		notification.setNotificationText(notificationType.getNotificationText());
		notification.setUserId(notificationType.getUserId());
		
		
		return notificationsDAO.createOrSaveNotifications(notification);
	}
	
	@GET
	@Path("getnotificationsbyclub/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public NotificationsListType getAllServicesByClubId(@PathParam("id") int id) {
		List<Notifications> lstNotifications = notificationsDAO.getAllNotificationsById("clubId",id);

		NotificationsListType notificationsListType = new NotificationsListType();

		for(Notifications notification : lstNotifications){
			NotificationsType notificationType = new NotificationsType();
			notificationType.setId(notification.getId());
			notificationType.setClubId(notification.getClubId());
			notificationType.setUserId(notification.getUserId());
			notificationType.setNotificationText(notification.getNotificationText());
			notificationType.setRead(notification.getIsRead());
			notificationType.setDate(notification.getDate());
			notificationsListType.getNotificationsType().add(notificationType);
		}
		return notificationsListType;
	}

	@GET
	@Path("getnotificationsbyuser/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public NotificationsListType getAllServicesByUserId(@PathParam("id") int id) {
		List<Notifications> lstNotifications = notificationsDAO.getAllNotificationsById("userId",id);

		NotificationsListType notificationsListType = new NotificationsListType();

		for(Notifications notification : lstNotifications){
			NotificationsType notificationType = new NotificationsType();
			notificationType.setId(notification.getId());
			notificationType.setClubId(notification.getClubId());
			notificationType.setUserId(notification.getUserId());
			notificationType.setNotificationText(notification.getNotificationText());
			notificationType.setRead(notification.getIsRead());
			notificationType.setDate(notification.getDate());
			notificationsListType.getNotificationsType().add(notificationType);
		}
		return notificationsListType;
	}

}
