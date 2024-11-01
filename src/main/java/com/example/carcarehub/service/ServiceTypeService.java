package com.example.carcarehub.service;

import com.example.carcarehub.domain.ServiceType;
import com.example.carcarehub.model.request.ServiceTypeRegisterRequest;
import com.example.carcarehub.model.response.ServiceTypeListResponse;
import com.example.carcarehub.model.response.ServiceTypeResponse;

import java.util.List;

public interface ServiceTypeService {
    public ServiceTypeResponse createServiceType(ServiceTypeRegisterRequest serviceTypeRegisterRequest) throws Exception;
    public ServiceTypeListResponse getServiceTypeById(int serviceTypeId) throws Exception;

    public ServiceType updateServiceType(int serviceTypeId, ServiceType updatedServiceTypeRequest)throws Exception;

    default boolean deleteServiceType(int serviceTypeId) {
        return false;
    }
}


