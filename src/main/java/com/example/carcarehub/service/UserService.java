package com.example.carcarehub.service;

import com.example.carcarehub.model.request.UserRegistrationRequest;
import com.example.carcarehub.model.response.UserRegistrationResponse;
import com.example.carcarehub.model.response.UserResponse;

public interface UserService {
   public UserRegistrationResponse createUser(UserRegistrationRequest userRegistrationRequest) throws Exception;
    public UserResponse getUserById(int userId) throws Exception;
}
