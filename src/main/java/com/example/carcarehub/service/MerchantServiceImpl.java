package com.example.carcarehub.service;

import com.example.carcarehub.model.request.MerchantRegistrationRequest;
import com.example.carcarehub.model.request.UpdateMerchantRequest;
import com.example.carcarehub.model.request.UpdateMerchantResponse;
import com.example.carcarehub.model.response.AllMerchantResponse;
import com.example.carcarehub.model.response.MerchantRegistrationResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MerchantServiceImpl implements MerchantService{
    @Override
    public MerchantRegistrationResponse createMerchant(MerchantRegistrationRequest merchantRegistrationRequest) throws Exception {
        return null;
    }

    @Override
    public List<AllMerchantResponse> getAllMerchants() throws Exception {
        return null;
    }

    @Override
    public UpdateMerchantResponse updateMerchantDetails(int userId, UpdateMerchantRequest updateMerchantRequest) throws Exception{
        return null;
    }
}
