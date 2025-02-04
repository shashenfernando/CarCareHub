package com.example.carcarehub.service;

import com.example.carcarehub.Dao.MerchantDao;
import com.example.carcarehub.Dao.UserCredentialDao;
import com.example.carcarehub.Dao.UserDao;
import com.example.carcarehub.domain.Merchant;
import com.example.carcarehub.domain.User;
import com.example.carcarehub.domain.UserCredential;
import com.example.carcarehub.enums.CarCareHubException;
import com.example.carcarehub.enums.Status;
import com.example.carcarehub.exception.AppException;
import com.example.carcarehub.model.request.UpdateUser;
import com.example.carcarehub.model.request.UserRegistrationRequest;
import com.example.carcarehub.model.response.*;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserServiceImpl implements UserService {

    @Autowired
    public UserDao userDao;
    @Autowired
    public UserCredentialDao userCredentialDao;
    @Autowired
    private MerchantDao merchantDao;


    @Override
    public UserRegistrationResponse createUser(UserRegistrationRequest userRegistrationRequest) throws Exception {

        User user = new User();
        UserCredential userCredential =new UserCredential();
        Date date = new Date();

        user.setFirstName(userRegistrationRequest.getFirstName());
        user.setLastName(userRegistrationRequest.getLastName());
        user.setNic(userRegistrationRequest.getNic());
        user.setMobileNumber(userRegistrationRequest.getMobileNumber());
        user.setCreateDate(date);
        user.setZipCode(userRegistrationRequest.getZipCode());
        user.setLatitude(userRegistrationRequest.getLatitude());
        user.setLongitude(userRegistrationRequest.getLongitude());
        user.setHomeTown(userRegistrationRequest.getHomeTown());
        user.setRoad(userRegistrationRequest.getRoad());
        user.setEmail(userRegistrationRequest.getEmail());
        userCredential.setUser(user);
        userCredential.setUserName(userRegistrationRequest.getUserName());
        userCredential.setPassword(userRegistrationRequest.getPassword());
        userCredential.setStatus(Status.APPROVED_STATUS.getStatus());
        userCredential.setRetryCount(userRegistrationRequest.getRetryCount());


        User existingUser = userDao.findUserByEmail(user.getEmail());

        if (existingUser != null){
            throw new AppException(CarCareHubException.THIS_EMAIL_ALREADY_EXIST);
        }

        String hashedPassword = hashedPassword(userCredential.getPassword());
        userCredential.setPassword(hashedPassword);

        userDao.registerUser(user);
        userCredentialDao.createUserCredential(userCredential);


        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setNic(user.getNic());
        response.setMobileNumber(user.getMobileNumber());
        response.setCreateDate(user.getCreateDate());
        response.setZipCode(user.getZipCode());
        response.setLatitude(user.getLatitude());
        response.setLongitude(user.getLongitude());
        response.setHomeTown(user.getHomeTown());
        response.setRoad(user.getRoad());
        response.setEmail(user.getEmail());
        response.setStatus(userCredential.getStatus());
        response.setRetryCount(userCredential.getRetryCount());

        return response;

    }

    private boolean isUserValid(User user) {
        return user.getEmail() != null && user.getEmail().equalsIgnoreCase(user.getEmail()) &&
                user.getHomeTown() != null && user.getHomeTown().equalsIgnoreCase(user.getEmail()) &&
                user.getFirstName() != null && !user.getFirstName().isEmpty() &&
                user.getLastName() != null && !user.getLastName().isEmpty() &&
                user.getNic() != null && !user.getNic().isEmpty() &&
                user.getMobileNumber() != null && !user.getMobileNumber().isEmpty() &&
                user.getLatitude() != null && !user.getLatitude().isEmpty() &&
                user.getLongitude() != null && !user.getLongitude().isEmpty() &&
                user.getZipCode() !=null && !user.getZipCode().isEmpty();

    }

    private String hashedPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public UserResponse getUserById(int userId) throws Exception {

        User user = userDao.findUserById(userId);

        UserCredential credential = new UserCredential();

        if (user == null){
            throw new AppException(CarCareHubException.USER_NOT_FOUND);
        }

        UserResponse response = new UserResponse();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setNic(user.getNic());
        response.setMobileNumber(user.getMobileNumber());
        response.setStatus(credential.getStatus());
        response.setZipCode(user.getZipCode());
        response.setCreateDate(user.getCreateDate());
        response.setEmail(user.getEmail());
        response.setLongitude(user.getLongitude());
        response.setLatitude(user.getLatitude());
        response.setHomeTown(user.getHomeTown());
        response.setRoad(user.getRoad());

        return response;
    }
    @Override
    public List<CarCareHubUserListResponse> getCarCareHubUsersList() throws Exception {

      List<User>users=userDao.getUserList();
      List<CarCareHubUserListResponse> responses = new ArrayList<>();

      for (User user :users){

          CarCareHubUserListResponse response = new CarCareHubUserListResponse();
          response.setFirstName(user.getFirstName());
          response.setLastName(user.getLastName());
          response.setNic(user.getNic());
          response.setMobileNumber(user.getMobileNumber());
          response.setCreateDate(user.getCreateDate());
          response.setZipCode(user.getZipCode());
          response.setHomeTown(user.getHomeTown());
          response.setRoad(user.getRoad());
          response.setEmail(user.getEmail());
          responses.add(response);
      }

      if (responses == null){
          throw new AppException(CarCareHubException.NO_USERS_DATA_FOUND);
      }
      else {
          return responses;
      }
    }

    @Override
    @Transactional
    public UpdateUserResponse updateUser(int userId, UpdateUser updateUser) throws Exception {

        User user = userDao.findUserById(userId);

        if (user == null){
            throw new AppException(CarCareHubException.USER_NOT_FOUND);
        }
        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setNic(updateUser.getNic());
        user.setMobileNumber(updateUser.getMobileNumber());
        user.setZipCode(updateUser.getZipCode());
        user.setHomeTown(updateUser.getHomeTown());
        user.setRoad(updateUser.getRoad());

        userDao.updateUser(user);

        if (user != null){
            UpdateUserResponse response = new UpdateUserResponse();
            response.setFirstName(user.getFirstName());
            response.setLastName(user.getLastName());
            response.setNic(user.getNic());
            response.setMobileNumber(user.getMobileNumber());
            response.setZipCode(user.getZipCode());
            response.setHomeTown(user.getHomeTown());
            response.setRoad(user.getRoad());

            return response;
        }
        else {
            throw new AppException(CarCareHubException.UNKNOWN_ERROR_OCCURED);
        }
    }

    @Override
    public HashMap<String, String> deleteCarCareHubUserById(int userid) throws Exception {

        HashMap<String, String> hashMap =new HashMap<>();

        User user = userDao.findUserById(userid);


        if (user == null){
            throw new AppException(CarCareHubException.USER_NOT_FOUND);
        }
        try {
            userCredentialDao.deleteUserCredentials(user);
            userDao.deleteUser(user);
            hashMap.put("status", "success");
            hashMap.put("message", "User deleted successfully.");
        }
        catch (Exception e){
            hashMap.put("status", "error");
            hashMap.put("message", "An error occurred while deleting the user: " + e.getMessage());
        }
        return hashMap;
    }
    @Override
    public ServiceStationResponse findStation(int stationId) throws Exception {

        Merchant merchant = merchantDao.findMerchantById(stationId);

        if (merchant == null){
            throw new AppException(CarCareHubException.MERCHANT_NOT_FOUND);
        }
        ServiceStationResponse response = new ServiceStationResponse();
        response.setStationId(merchant.getId());
        response.setStationName(merchant.getStationName());
        response.setRoad(merchant.getRoad());
        response.setMail(merchant.getBusinessEmail());
        response.setPhone(merchant.getBusinessMobileNumber());

        return response;
    }

}
