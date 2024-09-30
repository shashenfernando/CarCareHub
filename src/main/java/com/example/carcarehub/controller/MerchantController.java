package com.example.carcarehub.controller;

import com.example.carcarehub.model.request.*;
import com.example.carcarehub.model.response.*;
import com.example.carcarehub.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(method = RequestMethod.GET , value = "/allMerchants")
    public List<AllMerchantResponse> getAllMerchants() throws Exception{
        return merchantService.getAllMerchants();
    }
    @RequestMapping(method = RequestMethod.PUT , value = "/{userId}/updateCarCareMerchant")
    public UpdateMerchantResponse updateMerchant(@PathVariable("userId")int userId , @RequestBody UpdateMerchantRequest updateMerchantRequest) throws Exception{

        UpdateMerchantResponse response = merchantService.updateMerchantDetails(userId,updateMerchantRequest);
        CarCareHubResponse careHubResponse = new CarCareHubResponse();
        careHubResponse.setResponseCode("00");
        careHubResponse.setResponseObject(response);

        return response;
    }

}
