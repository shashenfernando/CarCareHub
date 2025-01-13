package com.example.carcarehub.controller;

import com.example.carcarehub.model.request.LoginRequest;
import com.example.carcarehub.model.response.CarCareHubResponse;
import com.example.carcarehub.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(method = RequestMethod.POST , value ="/login")
    public CarCareHubResponse Login(@RequestBody LoginRequest loginRequest) throws Exception {

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        String role = loginRequest.getRole();

        HashMap<String, Object> responseHashMap = loginService.loginApplication(email, password,role);

        CarCareHubResponse careHubResponse = new CarCareHubResponse();
        careHubResponse.setResponseCode("000");
        careHubResponse.setResponseObject(responseHashMap);

        return careHubResponse;
    }
}
