package com.example.carcarehub.controller;

import com.example.carcarehub.model.request.ReservationRequest;
import com.example.carcarehub.model.response.CarCareHubResponse;
import com.example.carcarehub.model.response.ReservationResponse;
import com.example.carcarehub.model.response.UserResponse;
import com.example.carcarehub.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    @RequestMapping(method = RequestMethod.POST , value ="/createReservation")
    public CarCareHubResponse createReservation(@RequestBody ReservationRequest reservationRequest) throws Exception {

        ReservationResponse response = reservationService.createReservation(reservationRequest);
        CarCareHubResponse careHubResponse = new CarCareHubResponse();
        careHubResponse.setResponseCode("000");
        careHubResponse.setResponseObject(response);

        return careHubResponse;

    }
    @RequestMapping(method = RequestMethod.GET , value = "/{merchantId}/getCarCareHubUser")
    public CarCareHubResponse getUserById(@PathVariable("merchantId")int merchantId) throws Exception{

        ReservationResponse response = reservationService.getReservationById(merchantId);
        CarCareHubResponse careHubResponse = new CarCareHubResponse();
        careHubResponse.setResponseCode("000");
        careHubResponse.setResponseObject(response);

        return careHubResponse;
    }

}
