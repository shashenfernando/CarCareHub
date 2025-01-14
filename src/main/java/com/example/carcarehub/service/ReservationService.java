package com.example.carcarehub.service;

import com.example.carcarehub.model.request.ReservationRequest;
import com.example.carcarehub.model.request.UserRegistrationRequest;
import com.example.carcarehub.model.response.ReservationResponse;
import com.example.carcarehub.model.response.UserRegistrationResponse;

import java.util.HashMap;
import java.util.List;

public interface ReservationService {
   public ReservationResponse createReservation(ReservationRequest reservationRequest) throws Exception;
  public ReservationResponse getReservationById(int reservationId) throws Exception;
    public List<ReservationResponse> getPendingReservation(int merchantId)throws Exception;
    public HashMap<String, Object> acceptReservation(int merchantId, int reservationId) throws Exception;
}
