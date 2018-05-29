package com.planetclubs.service;

import in.planetclubs.NotificationsListType;
import in.planetclubs.NotificationsType;

public interface INotificationsService {
	
	public String createOrSaveNotifications(NotificationsType notificationsType);

	public NotificationsListType getAllServicesByClubId(int id);
	
	public NotificationsListType getAllServicesByUserId(int id);
}
