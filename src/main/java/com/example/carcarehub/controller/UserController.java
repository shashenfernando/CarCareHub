package com.example.carcarehub.controller;

import com.example.carcarehub.model.request.UserRegistrationRequest;
import com.example.carcarehub.model.request.UserUpdateRequest;
import com.example.carcarehub.model.response.*;
import com.example.carcarehub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST , value ="/createUser")
    public CarCareHubResponse createNewUser(UserRegistrationRequest userRegistrationRequest) throws Exception{

        UserRegistrationResponse response = userService.createUser(userRegistrationRequest);
        CarCareHubResponse careHubResponse = new CarCareHubResponse();
        careHubResponse.setResponseCode("00");
        careHubResponse.setResponseObject(response);

        return careHubResponse;
    }
    @RequestMapping(method = RequestMethod.GET , value = "/allUsers")
    public List<CarCareAllUserResponse>getAllUsers() throws Exception{
        return userService.getAllUsers();
    }
    @RequestMapping(method = RequestMethod.PUT , value = "/{userId}/updateCarCareUser")
    public UserUpdateResponse updateUser(@PathVariable("userId")int userId , @RequestBody UserUpdateRequest userUpdateRequest) throws Exception{

        UserUpdateResponse response = userService.updateUserDetails(userId,userUpdateRequest);
        CarCareHubResponse careHubResponse = new CarCareHubResponse();
        careHubResponse.setResponseCode("00");
        careHubResponse.setResponseObject(response);

        return response;
    }

    @RequestMapping(method = RequestMethod.GET ,value = "/{userId}/searchUser")
    public FindUserResponse findCarCareUser(@PathVariable("userId")int userId) throws Exception{

        FindUserResponse response1=userService.findCarCareUser(userId);
        CarCareHubResponse response = new CarCareHubResponse();
        response.setResponseCode("00");
        response.setResponseObject(response1);

        return response1;
    }

    @RequestMapping(method = RequestMethod.DELETE , value = "/{userId}/deleteUser")
    public void deleteUser(@PathVariable("userId")int userId) throws Exception{

        CarCareHubResponse response = new CarCareHubResponse();
        response.setResponseCode("00");
    }
}
