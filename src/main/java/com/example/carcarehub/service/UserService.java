package com.example.carcarehub.service;

import com.example.carcarehub.model.request.UserRegistrationRequest;
import com.example.carcarehub.model.request.UserUpdateRequest;
import com.example.carcarehub.model.response.CarCareAllUserResponse;
import com.example.carcarehub.model.response.FindUserResponse;
import com.example.carcarehub.model.response.UserRegistrationResponse;
import com.example.carcarehub.model.response.UserUpdateResponse;

import java.util.List;

public interface UserService {
    //shashen

    public UserRegistrationResponse createUser(UserRegistrationRequest registrationRequest) throws Exception;
    public List<CarCareAllUserResponse> getAllUsers() throws Exception;
    public UserUpdateResponse updateUserDetails(int uerId, UserUpdateRequest userUpdateRequest) throws Exception;
    public FindUserResponse findCarCareUser(int userId) throws Exception;
    public void deleteUser(int userId) throws Exception;
}
