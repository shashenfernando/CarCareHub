package com.example.carcarehub.controller;

import com.example.carcarehub.Dao.MerchantDao;
import com.example.carcarehub.domain.Merchant;
import com.example.carcarehub.model.request.RegisterMerchantRequest;
import com.example.carcarehub.model.response.CarCareHubResponse;
import com.example.carcarehub.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchant")
public class MerchantController {
    @Autowired
    MerchantDao merchantDao;
    @Autowired
    MerchantService merchantService;


    @RequestMapping("/register")
    public CarCareHubResponse registerMerchant(@RequestBody RegisterMerchantRequest registerMerchantRequest) throws Exception {
        Merchant merchant = new Merchant();
        CarCareHubResponse response = new CarCareHubResponse();

        merchant = merchantService.merchantRegistration(registerMerchantRequest);
        return response;


    }



}
