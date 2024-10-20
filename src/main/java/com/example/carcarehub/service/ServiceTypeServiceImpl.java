package com.example.carcarehub.service;

import com.example.carcarehub.domain.ServiceType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceTypeServiceImpl implements ServiceTypeService {

    private final List<ServiceType> serviceTypeList = new ArrayList<>();
    private int currentId = 1;

    @Override
    public List<ServiceType> getAllServiceTypes() {
        return serviceTypeList;
    }

    @Override
    public ServiceType getServiceTypeById(Integer id) {
        return serviceTypeList.stream()
                .filter(serviceType -> serviceType.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public ServiceType createServiceType(ServiceType serviceType) {
        serviceType.setId(currentId++);
        serviceTypeList.add(serviceType);
        return serviceType;
    }

    @Override
    public ServiceType updateServiceType(Integer id, ServiceType updatedServiceType) {
        Optional<ServiceType> existingServiceTypeOpt = serviceTypeList.stream()
                .filter(serviceType -> serviceType.getId().equals(id))
                .findFirst();

        if (existingServiceTypeOpt.isPresent()) {
            ServiceType existingServiceType = existingServiceTypeOpt.get();
            existingServiceType.setServiceType(updatedServiceType.getServiceType());
            existingServiceType.setDescription(updatedServiceType.getDescription());
            existingServiceType.setStatus(updatedServiceType.getStatus());
            existingServiceType.setPrice(updatedServiceType.getPrice());
            existingServiceType.setAverageTime(updatedServiceType.getAverageTime());
            existingServiceType.setAvailableCount(updatedServiceType.getAvailableCount());
            existingServiceType.setVehicleType(updatedServiceType.getVehicleType());
            existingServiceType.setMerchant(updatedServiceType.getMerchant());
            return existingServiceType;
        }
        return null;
    }

    @Override
    public boolean deleteServiceType(Integer id) {
        return serviceTypeList.removeIf(serviceType -> serviceType.getId().equals(id));
    }
}