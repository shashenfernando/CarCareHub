package com.example.carcarehub.service;

import com.example.carcarehub.model.request.ReservationRequest;
import com.example.carcarehub.model.request.UserRegistrationRequest;
import com.example.carcarehub.model.response.ReservationResponse;
import com.example.carcarehub.model.response.UserRegistrationResponse;

public interface ReservationService {
   public ReservationResponse createReservation(ReservationRequest reservationRequest) throws Exception;
  public ReservationResponse getReservationById(int merchantId) throws Exception;
}
