package com.example.carcarehub.service;

import com.example.carcarehub.domain.User;
import com.example.carcarehub.exception.CarCareHubException;
import com.example.carcarehub.model.request.UserLoginRequest;
import com.example.carcarehub.model.request.UserRegistrationRequest;
import com.example.carcarehub.model.request.UserUpdateRequest;
import com.example.carcarehub.model.response.*;
import com.example.carcarehub.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserRegistrationResponse createUser(UserRegistrationRequest registrationRequest) throws Exception {

      User user = new User();
      user.setFirstName(registrationRequest.getFirstName());
      user.setLastName(registrationRequest.getLastName());
      user.setNic(registrationRequest.getNic());
      user.setMobileNumber(registrationRequest.getMobileNumber());
      user.setPassword(registrationRequest.getPassword());
      user.setConfirmPassword(registrationRequest.getConfirmPassword());
      user.setEmail(registrationRequest.getEmail());

         if (!isUserValid(user)){
             throw new Exception(CarCareHubException.INVALID_DATA);
         }
         User existingUser = userRepository.findUserByEmail(user.getEmail());
         if (existingUser != null){
             throw new Exception(CarCareHubException.THIS_EMAIL_ALREADY_EXIST);
         }

          String hashedPassword = hashedPassword(user.getPassword());
         user.setPassword(hashedPassword);
         user.setConfirmPassword(hashedPassword);

        userRepository.save(user);


        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setMobileNumber(user.getMobileNumber());
        response.setNic(user.getNic());
        response.setEmail(user.getEmail());

        return response;
    }

    private String hashedPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private boolean isUserValid(User user){
       return   user.getEmail() != null && user.getEmail().equalsIgnoreCase(user.getEmail()) &&
                user.getPassword() != null && user.getPassword().equalsIgnoreCase(user.getConfirmPassword()) &&
                user.getFirstName() != null && !user.getFirstName().isEmpty() &&
                user.getLastName() != null && !user.getLastName().isEmpty() &&
                user.getNic() != null && !user.getNic().isEmpty() &&
                user.getMobileNumber() != null && !user.getMobileNumber().isEmpty();


    }
    @Override
    public List<CarCareAllUserResponse> getAllUsers() throws Exception {

        List<User>userList=userRepository.findAll();

        if (userList == null || userList.isEmpty()){

            throw new Exception(CarCareHubException.NO_USERS_DATA_FOUND);
        }

        List<CarCareAllUserResponse> userResponses=new ArrayList<>();

        for (User user : userList){
            CarCareAllUserResponse userResponse = new CarCareAllUserResponse();
            userResponse.setId(user.getId());
            userResponse.setFirstName(user.getFirstName());
            userResponse.setLastName(user.getLastName());
            userResponse.setNic(user.getNic());
            userResponse.setMobileNumber(user.getMobileNumber());
            userResponse.setEmail(user.getEmail());
        }
         return userResponses;
    }
    @Override
    public UserUpdateResponse updateUserDetails(int uerId, UserUpdateRequest userUpdateRequest) throws Exception {

        User user = userRepository.findUserById(uerId);

        if (user == null){
            throw new Exception(CarCareHubException.USER_NOT_FOUND);
        }
         user.setFirstName(userUpdateRequest.getFirstName());
         user.setLastName(userUpdateRequest.getLastName());
         user.setNic(userUpdateRequest.getNic());
         user.setMobileNumber(userUpdateRequest.getMobileNumber());
         user.setEmail(userUpdateRequest.getEmail());

         userRepository.save(user);

        UserUpdateResponse response = new UserUpdateResponse();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setMobileNumber(user.getMobileNumber());
        response.setNic(user.getNic());
        response.setEmail(user.getEmail());

        return response;
    }
    @Override
    public FindUserResponse findCarCareUser(int userId) throws Exception{

        User user = userRepository.findUserById(userId);
        if (user == null){
            throw new Exception(CarCareHubException.USER_NOT_FOUND);
        }

        FindUserResponse response = new FindUserResponse();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setNic(user.getNic());
        response.setMobileNumber(user.getMobileNumber());
        response.setEmail(user.getEmail());

        return response;
    }
    @Override
    public void deleteUser(int userId) throws Exception{

        User user = userRepository.findUserById(userId);
      if (user == null){
          throw new Exception(CarCareHubException.INVALID_USER);
      }
      userRepository.deleteUserById(userId);
    }

    @Override
    public UserLoginResponse loginUser(UserLoginRequest userLoginRequest) throws Exception {
        User user = userRepository.findUserByEmail(userLoginRequest.getEmail());

        if (user == null){
            throw new Exception(CarCareHubException.INVALID_USER);
        }

    if (!userLoginRequest.getEmail().equalsIgnoreCase(user.getPassword())){
        throw new Exception(CarCareHubException.INVALID_PASSWORD);
    }

    UserLoginResponse response = new UserLoginResponse();
    response.setEmail(user.getEmail());
    response.setPassword(user.getPassword());

    return response;

    }
}
