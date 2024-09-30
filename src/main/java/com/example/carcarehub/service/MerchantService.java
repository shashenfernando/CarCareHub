package com.example.carcarehub.service;

import com.example.carcarehub.model.request.MerchantRegistrationRequest;
import com.example.carcarehub.model.response.MerchantRegistrationResponse;
import com.example.carcarehub.model.response.UserRegistrationResponse;

public interface MerchantService {

   public MerchantRegistrationResponse createMerchant(MerchantRegistrationRequest merchantRegistrationRequest);
}
