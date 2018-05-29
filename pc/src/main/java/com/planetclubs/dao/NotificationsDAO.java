package com.planetclubs.dao;

import java.util.List;

import com.planetclubs.model.Notifications;

public interface NotificationsDAO {

	List<Notifications> getAllNotificationsById(String field,int id);

	String createOrSaveNotifications(Notifications notification);
}
