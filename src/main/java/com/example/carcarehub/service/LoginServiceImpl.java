package com.example.carcarehub.service;

import com.example.carcarehub.Dao.AdminDao;
import com.example.carcarehub.Dao.MerchantDao;
import com.example.carcarehub.Dao.UserDao;
import com.example.carcarehub.Repository.AdminCredentialRepo;
import com.example.carcarehub.Repository.MerchantCredentialRepo;
import com.example.carcarehub.Repository.UserCredentialRepo;
import com.example.carcarehub.domain.*;
import com.example.carcarehub.enums.CarCareHubException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private MerchantDao merchantDao;
    @Autowired
    private UserCredentialRepo userCredentialRepo;
    @Autowired
    private MerchantCredentialRepo merchantCredentialRepo;
    @Autowired
    AdminCredentialRepo adminCredentialRepo;
    @Override
    public HashMap<String, Object> loginApplication(String email, String password, String role) throws Exception {

            if (email == null && password == null){
                throw new Exception(String.valueOf(CarCareHubException.EMAIL_AND_PASSWORD_MANDATORY));
            }


            HashMap<String, Object> hm = new HashMap<String, Object>();

            if (role.equalsIgnoreCase("user")){

                User user = userDao.findUserByEmail(email);

                if (user == null){
                    throw new Exception(String.valueOf(CarCareHubException.USER_NOT_FOUND));
                }

                // UserCredential userCredential = userCredentialDao.findCredentialsByUserId(user.getId());
                UserCredential userCredential = userCredentialRepo.findById(user.getId());

                if (userCredential == null){
                    throw  new Exception(String.valueOf(CarCareHubException.NO_USERS_DATA_FOUND));
                }

                boolean isMatch = BCrypt.checkpw(password, userCredential.getPassword());

                if (!isMatch){
                    throw new Exception(String.valueOf(CarCareHubException.INVALID_PASSWORD));
                }

                hm.put("status", "success");
                hm.put("message", "The login successful");
                hm.put("role","user");

            } else if (role.equalsIgnoreCase("merchant")) {

                Merchant merchant = merchantDao.findMerchantByEmail(email);

                if (merchant == null){
                    throw new Exception(String.valueOf(CarCareHubException.MERCHANT_NOT_FOUND));
                }

                MerchantCredential merchantCredential = merchantCredentialRepo.findById(merchant.getId());

                if (merchantCredential == null){
                    throw  new Exception(String.valueOf(CarCareHubException.MERCHANT_DATA_NOT_FOUND));
                }
                boolean isMatch = BCrypt.checkpw(password, merchantCredential.getPassword());

                if (!isMatch){
                    throw new Exception(String.valueOf(CarCareHubException.INVALID_PASSWORD));
                }
                hm.put("status", "success");
                hm.put("message", "The login successful");
                hm.put("role","merchant");

            }
            else {
                Admin admin = adminDao.findAdminByEmail(email);

                if (admin == null){
                    throw new Exception(String.valueOf(CarCareHubException.ADMIN_NOT_FOUND));
                }

                AdminCredential adminCredential = adminCredentialRepo.findById(admin.getId());

                boolean isMatch = BCrypt.checkpw(password, adminCredential.getPassword());

                if (!isMatch) {
                    throw new Exception(String.valueOf(CarCareHubException.INVALID_PASSWORD));
                }

                hm.put("status", "success");
                hm.put("message", "The login successful");
                hm.put("role","admin");
            }
            return hm;
        }

}
