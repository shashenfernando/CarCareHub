package com.example.carcarehub.service;

import com.example.carcarehub.Dao.MerchantCredentialDao;
import com.example.carcarehub.Dao.MerchantDao;
import com.example.carcarehub.domain.Merchant;
import com.example.carcarehub.domain.MerchantCredential;
import com.example.carcarehub.enums.Status;
import com.example.carcarehub.enums.ApplicationError;
import com.example.carcarehub.model.request.RegisterMerchantRequest;
import com.example.carcarehub.utill.MerchantEncryption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
public class MerchantServiceImpl implements MerchantService {

    private static final Logger logger = LoggerFactory.getLogger(MerchantServiceImpl.class);

    @Autowired
    private MerchantDao merchantDao;

    @Autowired
    private MerchantCredentialDao merchantCredentialDao;



    @Override
    @Transactional
    public Merchant merchantRegistration(RegisterMerchantRequest registerMerchantRequest) throws Exception {


        Merchant merchant = new Merchant();
        MerchantCredential merchantCredential = new MerchantCredential();

        try {
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
            Date date = new Date();
            merchant.setRegisteredDate(date);

            merchant = merchantDao.registerMerchant(merchant);

            if (merchant != null) {

                String encryptedPassword = MerchantEncryption.encrypt(registerMerchantRequest.getPassword());

                merchantCredential.setMerchant(merchant);
                merchantCredential.setPassword(encryptedPassword);
                merchantCredential.setUser_name(registerMerchantRequest.getUserName());
                merchantCredential.setStatus(Status.PENDING_STATUS.getStatus());
                merchantCredential.setRetryCount(1);

                merchantCredential = merchantCredentialDao.createMerchantCredential(merchantCredential);
                logger.info("merchant user password:::"+MerchantEncryption.decrypt(merchantCredential.getPassword()));

                if (merchantCredential != null) {
                    return merchant;
                } else {
                    throw new Exception(String.valueOf(ApplicationError.UNKNOWN_ERROR_OCCURED));

                }

            } else {
                throw new Exception(String.valueOf(ApplicationError.UNKNOWN_ERROR_OCCURED));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
}
