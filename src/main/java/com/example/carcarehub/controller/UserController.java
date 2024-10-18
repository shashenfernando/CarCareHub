package com.example.carcarehub.controller;

import com.example.carcarehub.model.request.UserRegistrationRequest;
import com.example.carcarehub.model.response.CarCareHubResponse;
import com.example.carcarehub.model.response.UserRegistrationResponse;
import com.example.carcarehub.service.UserService;
import org.apache.catalina.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST , value ="/createUser")
    public CarCareHubResponse createNewUser(@RequestBody UserRegistrationRequest userRegistrationRequest) throws Exception {


        UserRegistrationResponse response = userService.createUser(userRegistrationRequest);
        CarCareHubResponse careHubResponse = new CarCareHubResponse();
        careHubResponse.setResponseCode("00");
        careHubResponse.setResponseObject(response);

        return careHubResponse;

    }
}
