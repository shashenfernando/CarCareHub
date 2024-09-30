package com.example.carcarehub.service;

import com.example.carcarehub.model.request.MerchantRegistrationRequest;
import com.example.carcarehub.model.response.MerchantRegistrationResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MerchantServiceImpl implements MerchantService{
    @Override
    public MerchantRegistrationResponse createMerchant(MerchantRegistrationRequest merchantRegistrationRequest) {
        return null;
    }
}
