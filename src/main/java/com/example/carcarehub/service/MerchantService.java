package com.example.carcarehub.service;

import com.example.carcarehub.domain.Merchant;
import com.example.carcarehub.model.request.RegisterMerchantRequest;
public interface MerchantService {

    Merchant merchantRegistration(RegisterMerchantRequest registerMerchantRequest) throws Exception;
}
