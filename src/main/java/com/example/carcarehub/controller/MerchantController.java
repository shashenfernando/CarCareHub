package com.example.carcarehub.controller;

import com.example.carcarehub.model.request.MerchantRegistrationRequest;
import com.example.carcarehub.model.request.UserRegistrationRequest;
import com.example.carcarehub.model.response.CarCareHubResponse;
import com.example.carcarehub.model.response.MerchantRegistrationResponse;
import com.example.carcarehub.model.response.UserRegistrationResponse;
import com.example.carcarehub.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MerchantController {

    @Autowired
    private MerchantService merchantService;


    @RequestMapping(method = RequestMethod.POST , value ="/createMerchant")
    public CarCareHubResponse createNewMerchant(@RequestBody MerchantRegistrationRequest merchantRegistrationRequest) throws Exception{

        MerchantRegistrationResponse response = merchantService.createMerchant(merchantRegistrationRequest);
        CarCareHubResponse careHubResponse = new CarCareHubResponse();
        careHubResponse.setResponseCode("00");
        careHubResponse.setResponseObject(response);

        return careHubResponse;
    }

}
