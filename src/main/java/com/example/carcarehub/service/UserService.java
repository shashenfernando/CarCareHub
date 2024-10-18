package com.example.carcarehub.service;

import com.example.carcarehub.model.request.UserRegistrationRequest;
import com.example.carcarehub.model.response.UserRegistrationResponse;

public interface UserService {
   public UserRegistrationResponse createUser(UserRegistrationRequest userRegistrationRequest) throws Exception;
}
