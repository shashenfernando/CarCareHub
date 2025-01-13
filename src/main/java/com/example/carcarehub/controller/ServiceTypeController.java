package com.example.carcarehub.controller;

import com.example.carcarehub.domain.ServiceType;
import com.example.carcarehub.model.request.ServiceTypeRegisterRequest;
import com.example.carcarehub.model.response.CarCareHubResponse;
import com.example.carcarehub.model.response.ServiceTypeListResponse;
import com.example.carcarehub.model.response.ServiceTypeResponse;
import com.example.carcarehub.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/service-type")
public class ServiceTypeController {

    @Autowired
    private ServiceTypeService serviceTypeService;

    @RequestMapping(method = RequestMethod.POST, value = "/createServiceType")
    public CarCareHubResponse createServiceType(@RequestBody ServiceTypeRegisterRequest serviceTypeRequest) throws Exception {

        ServiceTypeResponse response = serviceTypeService.createServiceType(serviceTypeRequest);
        CarCareHubResponse carCareHubResponse = new CarCareHubResponse();
        carCareHubResponse.setResponseCode("00");
        carCareHubResponse.setResponseObject(response);

        return carCareHubResponse;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{serviceTypeId}/getServiceType")
    public CarCareHubResponse getServiceTypeById(@PathVariable("serviceTypeId") int serviceTypeId) throws Exception {

        ServiceTypeListResponse response = serviceTypeService.getServiceTypeById(serviceTypeId);
        CarCareHubResponse carCareHubResponse = new CarCareHubResponse();
        carCareHubResponse.setResponseCode("000");
        carCareHubResponse.setResponseObject(response);

        return carCareHubResponse;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{serviceTypeId}/updateServiceType")
    public CarCareHubResponse updateServiceType(@PathVariable("serviceTypeId") int serviceTypeId, @RequestBody ServiceType updatedServiceTypeRequest) throws Exception {

        ServiceType response = serviceTypeService.updateServiceType(serviceTypeId, updatedServiceTypeRequest);
        CarCareHubResponse carCareHubResponse = new CarCareHubResponse();
        carCareHubResponse.setResponseCode("00");
        carCareHubResponse.setResponseObject(response);

        return carCareHubResponse;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{serviceTypeId}/deleteServiceType")
    public CarCareHubResponse deleteServiceType(@PathVariable("serviceTypeId") int serviceTypeId) throws Exception {

        boolean isDeleted = serviceTypeService.deleteServiceType(serviceTypeId);
        CarCareHubResponse carCareHubResponse = new CarCareHubResponse();
        carCareHubResponse.setResponseCode(isDeleted ? "00" : "01");
        carCareHubResponse.setResponseObject(isDeleted ? "Service Type deleted successfully" : "Service Type not found");

        return carCareHubResponse;
    }
}
