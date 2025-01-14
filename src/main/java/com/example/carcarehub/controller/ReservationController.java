package com.example.carcarehub.controller;

import com.example.carcarehub.model.request.ReservationRequest;
import com.example.carcarehub.model.response.CarCareHubResponse;
import com.example.carcarehub.model.response.ReservationResponse;
import com.example.carcarehub.model.response.UserResponse;
import com.example.carcarehub.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @RequestMapping(method = RequestMethod.GET , value = "/{reservationId}/getReservation")
    public CarCareHubResponse getReservationById(@PathVariable("reservationId")int reservationId) throws Exception{

        ReservationResponse response = reservationService.getReservationById(reservationId);
        CarCareHubResponse careHubResponse = new CarCareHubResponse();
        careHubResponse.setResponseCode("000");
        careHubResponse.setResponseObject(response);

        return careHubResponse;
    }
    @RequestMapping(method = RequestMethod.POST, value = "/{merchantId}/getPendingReservation")
    public CarCareHubResponse getPendingReservation(@PathVariable("merchantId")int merchantId) throws Exception{

        List<ReservationResponse> getPendingReservation = reservationService.getPendingReservation(merchantId);
        CarCareHubResponse careHubResponse = new CarCareHubResponse();
        careHubResponse.setResponseCode("000");
        careHubResponse.setResponseObject(getPendingReservation);

        return careHubResponse;
    }

}
