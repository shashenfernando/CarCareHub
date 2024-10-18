package com.example.carcarehub.service;

import com.example.carcarehub.Dao.UserCredentialRepository;
import com.example.carcarehub.Dao.UserRepository;
import com.example.carcarehub.domain.User;
import com.example.carcarehub.domain.UserCredential;
import com.example.carcarehub.exception.CarCareHubException;
import com.example.carcarehub.model.request.UserRegistrationRequest;
import com.example.carcarehub.model.response.UserRegistrationResponse;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepository;
    @Autowired
    public UserCredentialRepository userCredentialRepository;

    @Override
    public UserRegistrationResponse createUser(UserRegistrationRequest userRegistrationRequest) throws Exception {

        User user = new User();
        UserCredential userCredential =new UserCredential();

        user.setFirstName(userRegistrationRequest.getFirstName());
        user.setLastName(userRegistrationRequest.getLastName());
        user.setNic(userRegistrationRequest.getNic());
        user.setMobileNumber(userRegistrationRequest.getMobileNumber());
        user.setCreateDate(userRegistrationRequest.getCreateDate());
        user.setZipCode(userRegistrationRequest.getZipCode());
        user.setLatitude(userRegistrationRequest.getLatitude());
        user.setLongitude(userRegistrationRequest.getLongitude());
        user.setHomeTown(userRegistrationRequest.getHomeTown());
        user.setRoad(userRegistrationRequest.getRoad());
        user.setEmail(userRegistrationRequest.getEmail());
        userCredential.setUserName(userRegistrationRequest.getUserName());
        userCredential.setPassword(userRegistrationRequest.getPassword());
        userCredential.setStatus(userRegistrationRequest.getStatus());
        userCredential.setRetryCount(userRegistrationRequest.getRetryCount());

//        if (!isUserValid(user)){
//            throw new Exception(CarCareHubException.INVALID_DATA);
//        }
        User existingUser = userRepository.findUserByEmail(user.getEmail());
        if (existingUser != null){
            throw new Exception(CarCareHubException.THIS_EMAIL_ALREADY_EXIST);
        }
        String hashedPassword = hashedPassword(userCredential.getPassword());
        userCredential.setPassword(hashedPassword);

        userRepository.save(user);
        userCredentialRepository.save(userCredential);


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


}
