package com.example.carcarehub.service;

import com.example.carcarehub.Dao.MerchantDao;
import com.example.carcarehub.domain.Merchant;
import com.example.carcarehub.domain.MerchantCredential;
import com.example.carcarehub.enums.Status;
import com.example.carcarehub.enums.ApplicationError;
import com.example.carcarehub.model.request.RegisterMerchantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;

@Primary
@Service
public class MerchantServiceImpl implements MerchantService{
    @Autowired
    private MerchantDao merchantDao;

    @Override
    public Merchant merchantRegistration(RegisterMerchantRequest registerMerchantRequest) throws Exception {



        Merchant merchant = new Merchant();
        MerchantCredential merchantCredential = new MerchantCredential();

        merchant.setStationName(registerMerchantRequest.getStationName());
        merchant.setHomeTown(registerMerchantRequest.getHomeTown());
        merchant.setRoad(registerMerchantRequest.getRoad());
        merchant.setLongitude(registerMerchantRequest.getLongitude());
        merchant.setLatitude(registerMerchantRequest.getLatitude());
        merchant.setZipCode(registerMerchantRequest.getZipCode());
        merchant.setBusinessEmail(registerMerchantRequest.getBusinessEmail());
        merchant.setBusinessMobileNumber(registerMerchantRequest.getBusinessMobileNumber());
        merchant.setBusinessRegNo(registerMerchantRequest.getBusinessRegNo());
        merchant.setStatus(Status.PENDING_STATUS.getStatus());
        Date date =new Date();
        merchant.setRegisteredDate(date);

        merchant = merchantDao.registerMerchant(merchant);

        if (merchant != null){
            merchantCredential.setPassword(registerMerchantRequest.getPassword());

        }else {
            throw new Exception(String.valueOf(ApplicationError.UNKNOWN_ERROR_OCCURED));
        }
        return null;

//TODO







    }
}
