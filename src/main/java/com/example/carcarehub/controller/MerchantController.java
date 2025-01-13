package com.example.carcarehub.controller;

import com.example.carcarehub.domain.Merchant;
import com.example.carcarehub.model.request.MerchantRequest;
import com.example.carcarehub.model.response.CarCareHubResponse;
import com.example.carcarehub.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchant")
public class MerchantController {

    @Autowired
    MerchantService merchantService;


    @PostMapping("/register")
    public CarCareHubResponse registerMerchant(@RequestBody MerchantRequest merchantRequest) throws Exception {

        CarCareHubResponse response = new CarCareHubResponse();

        Merchant merchant = merchantService.merchantRegistration(merchantRequest);
        if (merchant != null) {
            response.setResponseCode("00");
            response.setResponseObject(merchant);
        } else {
            response.setResponseCode("999");
        }
        return response;
    }
    @PostMapping("/getByUserName")
    public CarCareHubResponse getMerchantByUserName(@RequestBody MerchantRequest merchantRequest) throws Exception {

        CarCareHubResponse response = new CarCareHubResponse();

        Merchant merchant = merchantService.getMerchantByUserName(merchantRequest);
        if (merchant != null) {
            response.setResponseCode("00");
            response.setResponseObject(merchant);
        } else {
            response.setResponseCode("999");
        }
        return response;
    }

    @PutMapping("/update")
    public CarCareHubResponse updateMerchant(@RequestBody MerchantRequest merchantRequest) throws Exception {
        CarCareHubResponse response = new CarCareHubResponse();
        Merchant merchant = merchantService.updateMerchant(merchantRequest);
        if (merchant != null) {
            response.setResponseCode("00");
            response.setResponseObject(merchant);
        } else {
            response.setResponseCode("999");
        }
        return response;
    }
    @DeleteMapping("/delete/{id}")
    public CarCareHubResponse deleteMerchant(@PathVariable Integer id) throws Exception {
        CarCareHubResponse response = new CarCareHubResponse();
        boolean isDeleted = merchantService.deleteMerchant(id);
        if (isDeleted) {
            response.setResponseCode("00");
        } else {
            response.setResponseCode("999");
        }
        return response;
    }

}
