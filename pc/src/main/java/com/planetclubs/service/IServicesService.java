package com.planetclubs.service;

import com.planetclubs.model.Services;

import in.planetclubs.ServiceListType;

public interface IServicesService {

	public ServiceListType getAllServicesByClubId(int id);

	public Services getServicesByServiceId(int serviceId);
}
