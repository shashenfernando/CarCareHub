package com.example.carcarehub.service;

import com.example.carcarehub.Dao.MerchantCredentialDao;
import com.example.carcarehub.Dao.MerchantDao;
import com.example.carcarehub.domain.Merchant;
import com.example.carcarehub.domain.MerchantCredential;
import com.example.carcarehub.enums.Status;
import com.example.carcarehub.enums.ApplicationError;
import com.example.carcarehub.exception.CarCareHubException2;
import com.example.carcarehub.model.request.MerchantRequest;
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
    public Merchant merchantRegistration(MerchantRequest merchantRequest) throws Exception {


        Merchant merchant = new Merchant();
        MerchantCredential merchantCredential = new MerchantCredential();
        Merchant isMerchantExist =merchantDao.findByStationName(merchantRequest.getStationName());
        MerchantCredential isCredentialEixt = merchantCredentialDao.findByUserName(merchantRequest.getUserName());

        if (isMerchantExist != null) {
            throw new CarCareHubException2(ApplicationError.EXISTING_MERCHANT_NAME);
        }
        if (isCredentialEixt !=null){
            throw new CarCareHubException2(ApplicationError.EXISTING_USER_NAME);
        }
        try {

            merchant.setStationName(merchantRequest.getStationName());
            merchant.setHomeTown(merchantRequest.getHomeTown());
            merchant.setRoad(merchantRequest.getRoad());
            merchant.setLongitude(merchantRequest.getLongitude());
            merchant.setLatitude(merchantRequest.getLatitude());
            merchant.setZipCode(merchantRequest.getZipCode());
            merchant.setBusinessEmail(merchantRequest.getBusinessEmail());
            merchant.setBusinessMobileNumber(merchantRequest.getBusinessMobileNumber());
            merchant.setBusinessRegNo(merchantRequest.getBusinessRegNo());
            merchant.setStatus(Status.PENDING_STATUS.getStatus());
            Date date = new Date();
            merchant.setRegisteredDate(date);

            merchant = merchantDao.registerMerchant(merchant);

            if (merchant != null) {

                String encryptedPassword = MerchantEncryption.encrypt(merchantRequest.getPassword());

                merchantCredential.setMerchant(merchant);
                merchantCredential.setPassword(encryptedPassword);
                merchantCredential.setUser_name(merchantRequest.getUserName());
                merchantCredential.setStatus(Status.PENDING_STATUS.getStatus());
                merchantCredential.setRetryCount(1);

                merchantCredential = merchantCredentialDao.createMerchantCredential(merchantCredential);
                logger.info("merchant user password:::"+MerchantEncryption.decrypt(merchantCredential.getPassword()));

                if (merchantCredential != null) {
                    return merchant;
                } else {
                    throw new CarCareHubException2(ApplicationError.UNKNOWN_ERROR_OCCURED);
                }

            } else {
                throw new CarCareHubException2(ApplicationError.UNKNOWN_ERROR_OCCURED);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public Merchant getMerchantByUserName(MerchantRequest merchantRequest) throws Exception {
        String userName = merchantRequest.getUserName();
        if (userName != null && !userName.isEmpty()){
            try {
                MerchantCredential merchantCredential = merchantCredentialDao.findByUserName(userName);
                if (merchantCredential != null) {
                    Merchant merchantDetails = new Merchant();
                    Merchant merchant =  merchantCredential.getMerchant();
                    merchantDetails.setStationName(merchant.getStationName());
                    merchantDetails.setStatus(merchant.getStatus());
                    merchantDetails.setLatitude(merchant.getLatitude());
                    merchantDetails.setLongitude(merchant.getLongitude());
                    merchantDetails.setHomeTown(merchant.getHomeTown());
                    return merchantDetails;
                }
                return null;
            } catch (Exception e) {
                logger.error("Error: ", e);
                return null;
            }
        }else {
            throw new Exception(String.valueOf(ApplicationError.MISSING_SOME_PARAMETERS));
        }
    }

    @Override
    public Merchant updateMerchant(MerchantRequest merchantRequest) throws Exception {

        try {
            Merchant merchant = merchantDao.findMerchantById(merchantRequest.getId());
            if (merchant != null) {
                merchant.setStationName(merchantRequest.getStationName());
                merchant.setHomeTown(merchantRequest.getHomeTown());
                merchant.setRoad(merchantRequest.getRoad());
                merchant.setLongitude(merchantRequest.getLongitude());
                merchant.setLatitude(merchantRequest.getLatitude());
                merchant.setZipCode(merchantRequest.getZipCode());
                merchant.setBusinessEmail(merchantRequest.getBusinessEmail());
                merchant.setBusinessMobileNumber(merchantRequest.getBusinessMobileNumber());
                merchant.setBusinessRegNo(merchantRequest.getBusinessRegNo());

                return merchantDao.updateMerchant(merchant);
            }
            return null;
        } catch (Exception e) {
            logger.error("Error updating merchant: ", e);
            throw new Exception("Unable to update merchant");
        }
    }

    @Override
    public boolean deleteMerchant(Integer id) throws Exception {
        try {
            Merchant merchant = merchantDao.findMerchantById(id);
            if (merchant != null) {
                merchantCredentialDao.deleteMerchantCredentials(merchant);
                merchantDao.deleteMerchant(merchant);
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("Error deleting merchant: ", e);
            return false;
        }
    }
}
