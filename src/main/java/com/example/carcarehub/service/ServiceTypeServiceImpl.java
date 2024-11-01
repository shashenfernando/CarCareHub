package com.example.carcarehub.service;

import com.example.carcarehub.Dao.ServiceTypeDao;
import com.example.carcarehub.domain.Merchant;
import com.example.carcarehub.domain.ServiceType;
import com.example.carcarehub.domain.VehicleType;
import com.example.carcarehub.enums.CarCareHubException;
import com.example.carcarehub.enums.Status;
import com.example.carcarehub.model.request.ServiceTypeRegisterRequest;
import com.example.carcarehub.model.response.ServiceTypeListResponse;
import com.example.carcarehub.model.response.ServiceTypeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Component
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ServiceTypeServiceImpl implements ServiceTypeService {
    @Autowired
    ServiceTypeDao serviceTypeDao;


    @Override
    public ServiceTypeResponse createServiceType(ServiceTypeRegisterRequest serviceTypeRegisterRequest) throws Exception {
        ServiceType serviceType = new ServiceType();
        Merchant merchant = new Merchant();
        VehicleType vehicleType = new VehicleType();

        serviceType.setServiceType(serviceTypeRegisterRequest.getServiceType());
        serviceType.setVehicleType(serviceTypeRegisterRequest.getVehicleType());
        serviceType.setStatus(Status.APPROVED_STATUS.getStatus());
        serviceType.setDescription(serviceTypeRegisterRequest.getDescription());
        serviceType.setAverageTime(serviceTypeRegisterRequest.getAverageTime());
        serviceType.setPrice(serviceTypeRegisterRequest.getPrice());
        serviceType.setMerchant(merchant);
        serviceType.setVehicleType(vehicleType);
        serviceTypeDao.registerServiceType(serviceType);

        ServiceTypeResponse response = new ServiceTypeResponse();
        response.setServiceType(serviceType.getServiceType());
        response.setVehicleType(serviceType.getVehicleType().getName());
        response.setDescription(serviceType.getDescription());
        response.setAverageTime(serviceType.getAverageTime());
        response.setVehicleType(serviceType.getVehicleType().toString());

        return response;
    }

    @Override
    public ServiceTypeListResponse getServiceTypeById(int serviceTypeId) throws Exception {

        ServiceType serviceType = serviceTypeDao.findServiceTypeById(serviceTypeId);

        if (serviceType != null){
            throw new Exception(String.valueOf(CarCareHubException.SERVICE_TYPE_NOT_FOUND));
        }
        ServiceTypeListResponse response = new ServiceTypeListResponse();
        response.setId(serviceType.getId());
        response.setServiceType(serviceType.getServiceType());
        response.setDescription(serviceType.getDescription());
        response.setStatus(serviceType.getStatus());
        response.setPrice(serviceType.getPrice());
        response.setAvailableCount(serviceType.getAvailableCount());
        response.setStatus(serviceType.getStatus());

        if (response != null){
            throw new Exception(String.valueOf(CarCareHubException.DATA_NOT_FOUND));
        }
        return response;

    }

    @Override
    public ServiceType updateServiceType(int serviceTypeId, ServiceType updatedServiceTypeRequest) {
        return null;
    }

}