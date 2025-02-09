package com.example.carcarehub.service;

import com.example.carcarehub.Dao.AdminDao;
import com.example.carcarehub.Dao.MerchantDao;
import com.example.carcarehub.Dao.UserCredentialDao;
import com.example.carcarehub.Dao.UserDao;
import com.example.carcarehub.Repository.AdminCredentialRepo;
import com.example.carcarehub.Repository.MerchantCredentialRepo;
import com.example.carcarehub.Repository.UserCredentialRepo;
import com.example.carcarehub.domain.*;
import com.example.carcarehub.enums.CarCareHubException;
import com.example.carcarehub.enums.Status;
import com.example.carcarehub.exception.AppException;
import com.example.carcarehub.model.request.resetPasswordResetRequest;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

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
    @Autowired
    private EmailSenderService emailSenderService;

    @Override
    public HashMap<String, Object> loginApplication(String email, String password, String role) throws Exception {

            if (email == null && password == null){
                throw new AppException(CarCareHubException.EMAIL_AND_PASSWORD_MANDATORY);
            }


            HashMap<String, Object> hm = new HashMap<String, Object>();

            if (role.equalsIgnoreCase("user")){

                User user = userDao.findUserByEmail(email);

                if (user == null){
                    throw new AppException(CarCareHubException.USER_NOT_FOUND);
                }

                // UserCredential userCredential = userCredentialDao.findCredentialsByUserId(user.getId());
                UserCredential userCredential = userCredentialRepo.findById(user.getId());

                if (userCredential == null){
                    throw  new AppException(CarCareHubException.NO_USERS_DATA_FOUND);
                }

                boolean isMatch = BCrypt.checkpw(password, userCredential.getPassword());

                if (!isMatch){
                    throw new AppException(CarCareHubException.INVALID_PASSWORD);
                }

                hm.put("status", "success");
                hm.put("message", "The login successful");
                hm.put("role","user");

            } else if (role.equalsIgnoreCase("merchant")) {

                Merchant merchant = merchantDao.findMerchantByEmail(email);

                if (merchant == null){
                    throw new AppException(CarCareHubException.MERCHANT_NOT_FOUND);
                }

                MerchantCredential merchantCredential = merchantCredentialRepo.findById(merchant.getId());

                if (merchantCredential == null){
                    throw  new AppException(CarCareHubException.MERCHANT_DATA_NOT_FOUND);
                }
                boolean isMatch = BCrypt.checkpw(password, merchantCredential.getPassword());

                if (!isMatch){
                    throw new AppException(CarCareHubException.INVALID_PASSWORD);
                }
                hm.put("status", "success");
                hm.put("message", "The login successful");
                hm.put("role","merchant");

            }
            else {
                Admin admin = adminDao.findAdminByEmail(email);

                if (admin == null){
                    throw new AppException(CarCareHubException.ADMIN_NOT_FOUND);
                }

                AdminCredential adminCredential = adminCredentialRepo.findById(admin.getId());

                boolean isMatch = BCrypt.checkpw(password, adminCredential.getPassword());

                if (!isMatch) {
                    throw new AppException(CarCareHubException.INVALID_PASSWORD);
                }

                hm.put("status", "success");
                hm.put("message", "The login successful");
                hm.put("role","admin");
            }
            return hm;
        }

    @Override
    public HashMap<String, Object> resetPasswordReset(int userId, resetPasswordResetRequest request) throws Exception {

       User user = userDao.findUserById(userId);
       UserCredential credential = userCredentialRepo.findById(userId);
       if (user == null){
           throw new AppException(CarCareHubException.USER_NOT_FOUND);
       }
       Random random = new Random();
       String otp = String.format("%04d", random.nextInt(9999));

        HashMap<String, Object> hm = new HashMap<String, Object>();

        String userEmail = user.getEmail();
        String from = null;
        String subject = null;
        String body = null;

        subject = "Reset Password OTP";
        body = "Dear Customer,\n\n your please use this OTP"+otp +" for reset your password";
        emailSenderService.sendEmail(userEmail,from,subject,body);

        hm.put("status","true");
        hm.put("message", "Verification code successfully sent to your email. Use it to change the password");

        return hm;
    }

}
