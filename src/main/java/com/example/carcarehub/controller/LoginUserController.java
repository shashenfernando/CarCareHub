package com.example.carcarehub.controller;

import com.example.carcarehub.model.request.UserLoginRequest;
import com.example.carcarehub.model.response.CarCareHubResponse;
import com.example.carcarehub.model.response.UserLoginResponse;
import com.example.carcarehub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/login")
public class LoginUserController {

    @Autowired
    private UserService userService;

@RequestMapping(method = RequestMethod.POST , value = "/userLogin")
public UserLoginResponse userLogin(UserLoginRequest userLoginRequest) throws Exception{

    UserLoginResponse userLoginResponse = userService.loginUser(userLoginRequest);
    CarCareHubResponse response = new CarCareHubResponse();
    response.setResponseCode("00");
    response.setResponseObject(userLoginResponse);

    return userLoginResponse;
}
}
