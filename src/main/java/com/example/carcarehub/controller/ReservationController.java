package com.example.carcarehub.controller;

import com.example.carcarehub.model.request.ReservationRequest;
import com.example.carcarehub.model.response.CarCareHubResponse;
import com.example.carcarehub.model.response.ReservationResponse;
import com.example.carcarehub.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
