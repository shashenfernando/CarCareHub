package com.example.carcarehub.controller;

import com.example.carcarehub.model.request.ReservationRequest;
import com.example.carcarehub.model.request.UpdateReservationRequest;
import com.example.carcarehub.model.response.*;
import com.example.carcarehub.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    @RequestMapping(method = RequestMethod.GET, value = "/{merchantId}/getPendingReservation")
    public CarCareHubResponse getPendingReservation(@PathVariable("merchantId")int merchantId) throws Exception{

        List<ReservationResponse> getPendingReservation = reservationService.getPendingReservation(merchantId);
        CarCareHubResponse careHubResponse = new CarCareHubResponse();
        careHubResponse.setResponseCode("000");
        careHubResponse.setResponseObject(getPendingReservation);

        return careHubResponse;
    }
    @RequestMapping(method = RequestMethod.PUT,value = "/{merchantId}/acceptReservation/{reservationId}")
    public CarCareHubResponse acceptReservation(@PathVariable("merchantId") int merchantId,@PathVariable("reservationId")int reservationId) throws Exception{

        HashMap<String, Object> responseHashMap = reservationService.acceptReservation(merchantId,reservationId);
        CarCareHubResponse careHubResponse = new CarCareHubResponse();
        careHubResponse.setResponseCode("000");
        careHubResponse.setResponseObject(responseHashMap);

        return careHubResponse;
    }
    @RequestMapping(method = RequestMethod.PUT,value = "/{reservationId}/updateStatus/{merchantId}")
    public CarCareHubResponse updateStatus(@PathVariable("reservationId") int reservationId, @PathVariable("merchantId")int merchantId,@RequestParam(value = "status",required = false)String status) throws Exception{

        ReservationStatusUpdate response = reservationService.updateStatus(reservationId,merchantId,status);
        CarCareHubResponse careHubResponse = new CarCareHubResponse();
        careHubResponse.setResponseCode("000");
        careHubResponse.setResponseObject(response);

        return careHubResponse;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/{reservationId}/delete/reservation")
    public CarCareHubResponse deleteReservation(@PathVariable("reservationId")int reservationId) throws Exception{

        HashMap<String,Object> response = reservationService.deleteReservation(reservationId);
        CarCareHubResponse careHubResponse = new CarCareHubResponse();
        careHubResponse.setResponseCode("000");
        careHubResponse.setResponseObject(response);

        return careHubResponse;
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/{reservationId}/update/reservation")
    public CarCareHubResponse updateReservation(@PathVariable("reservationId") int reservationId , @RequestBody UpdateReservationRequest updateReservationRequest) throws Exception{

        UpdateReservationResponse response = reservationService.updateReservation(reservationId, updateReservationRequest);
        CarCareHubResponse careHubResponse = new CarCareHubResponse();
        careHubResponse.setResponseCode("000");
        careHubResponse.setResponseObject(response);

        return careHubResponse;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{reservationId}/findReservationStatus")
    private CarCareHubResponse getReservationStatus(@PathVariable("reservationId") int reservationId) throws Exception{

        ReservationStatusResponse response = reservationService.getReservationStatus(reservationId);
        CarCareHubResponse careHubResponse = new CarCareHubResponse();
        careHubResponse.setResponseCode("000");
        careHubResponse.setResponseObject(response);

        return careHubResponse;
    }
}
