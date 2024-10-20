package com.example.carcarehub.service;

import com.example.carcarehub.domain.ServiceType;

import java.util.List;

public interface ServiceTypeService {
    List<ServiceType> getAllServiceTypes();
    ServiceType getServiceTypeById(Integer id);
    ServiceType createServiceType(ServiceType serviceType);
    ServiceType updateServiceType(Integer id, ServiceType serviceType);
    boolean deleteServiceType(Integer id);
}
