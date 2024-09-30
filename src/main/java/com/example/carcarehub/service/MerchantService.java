package com.example.carcarehub.service;

import com.example.carcarehub.model.request.MerchantRegistrationRequest;
import com.example.carcarehub.model.request.UpdateMerchantRequest;
import com.example.carcarehub.model.request.UpdateMerchantResponse;
import com.example.carcarehub.model.response.AllMerchantResponse;
import com.example.carcarehub.model.response.MerchantRegistrationResponse;
import com.example.carcarehub.model.response.UserRegistrationResponse;

import java.util.List;

public interface MerchantService {

   public MerchantRegistrationResponse createMerchant(MerchantRegistrationRequest merchantRegistrationRequest) throws Exception;

   public List<AllMerchantResponse> getAllMerchants() throws Exception;

    public UpdateMerchantResponse updateMerchantDetails(int userId, UpdateMerchantRequest updateMerchantRequest) throws Exception;
}
