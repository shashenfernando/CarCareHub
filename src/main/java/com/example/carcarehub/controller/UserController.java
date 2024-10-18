package com.example.carcarehub.controller;

import com.example.carcarehub.model.request.UserRegistrationRequest;
import com.example.carcarehub.model.response.CarCareHubResponse;
import com.example.carcarehub.model.response.UserRegistrationResponse;
import com.example.carcarehub.model.response.UserResponse;
import com.example.carcarehub.service.UserService;
import org.apache.catalina.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.GET , value = "/{userId}/getCarCareHubUser")
    public CarCareHubResponse getUserById(@PathVariable("userId")int userId) throws Exception{

        UserResponse response = userService.getUserById(userId);
        CarCareHubResponse careHubResponse = new CarCareHubResponse();
        careHubResponse.setResponseCode("000");
        careHubResponse.setResponseObject(response);

        return careHubResponse;
    }
}
