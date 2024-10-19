package com.example.carcarehub.service;

import com.example.carcarehub.domain.Merchant;
import com.example.carcarehub.model.request.MerchantRequest;
public interface MerchantService {

    Merchant merchantRegistration(MerchantRequest merchantRequest) throws Exception;

    Merchant getMerchantByUserName(MerchantRequest merchantRequest) throws Exception;

    Merchant updateMerchant(MerchantRequest merchantRequest)throws Exception;

    boolean deleteMerchant(Integer id) throws Exception;
}
