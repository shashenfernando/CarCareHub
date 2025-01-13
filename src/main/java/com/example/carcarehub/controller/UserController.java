package com.example.carcarehub.controller;

import com.example.carcarehub.model.request.UpdateUser;
import com.example.carcarehub.model.request.UserRegistrationRequest;
import com.example.carcarehub.model.response.*;
import com.example.carcarehub.service.UserService;
import org.apache.catalina.User;
import org.apache.catalina.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST , value ="/createUser")
    public CarCareHubResponse createNewUser(@RequestBody UserRegistrationRequest userRegistrationRequest) throws Exception {


        UserRegistrationResponse response = userService.createUser(userRegistrationRequest);
        CarCareHubResponse careHubResponse = new CarCareHubResponse();
        careHubResponse.setResponseCode("000");
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

    @RequestMapping(method = RequestMethod.GET , value = "/getCarCareHubUsersList")
    public CarCareHubResponse getUsersList() throws Exception{

        List<CarCareHubUserListResponse> responses= userService.getCarCareHubUsersList();
        CarCareHubResponse response = new CarCareHubResponse();
        response.setResponseCode("000");
        response.setResponseObject(responses);

        return response;
    }

    @RequestMapping(method = RequestMethod.PUT , value = "/updateUser/{userId}/CarCareHub")
    public CarCareHubResponse updateUser(@PathVariable ("userId") int userId , @RequestBody UpdateUser updateUser) throws Exception{

        UpdateUserResponse response = userService.updateUser(userId ,updateUser);
        CarCareHubResponse careHubResponse = new CarCareHubResponse();
        careHubResponse.setResponseCode("000");
        careHubResponse.setResponseObject(response);

        return careHubResponse;
    }

    @RequestMapping(method = RequestMethod.DELETE , value = "/{userId}/deleteUser")
    public CarCareHubResponse deleteUser(@PathVariable("userId") int userid) throws Exception{

        HashMap<String,String> hashMap = userService.deleteCarCareHubUserById(userid);
        CarCareHubResponse careHubResponse = new CarCareHubResponse();
        careHubResponse.setResponseCode("000");
        careHubResponse.setResponseObject(hashMap);

        return careHubResponse;
    }
    @RequestMapping(method = RequestMethod.GET , value = "/{stationId}/findStation")
    public CarCareHubResponse findStation(@PathVariable("stationId")int stationId) throws Exception{

        ServiceStationResponse response = userService.findStation(stationId);
        CarCareHubResponse careHubResponse = new CarCareHubResponse();
        careHubResponse.setResponseCode("000");
        careHubResponse.setResponseObject(response);

        return careHubResponse;
    }

}
