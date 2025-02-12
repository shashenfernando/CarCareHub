package com.example.carcarehub.service;

import com.example.carcarehub.model.request.ReservationRequest;
import com.example.carcarehub.model.request.UpdateReservationRequest;
import com.example.carcarehub.model.response.ReservationResponse;
import com.example.carcarehub.model.response.ReservationStatusResponse;
import com.example.carcarehub.model.response.ReservationStatusUpdate;
import com.example.carcarehub.model.response.UpdateReservationResponse;

import java.util.HashMap;
import java.util.List;

public interface ReservationService {
   public ReservationResponse createReservation(ReservationRequest reservationRequest) throws Exception;
  public ReservationResponse getReservationById(int reservationId) throws Exception;
    public List<ReservationResponse> getPendingReservation(int merchantId)throws Exception;
    public HashMap<String, Object> acceptReservation(int merchantId, int reservationId) throws Exception;
    public ReservationStatusUpdate updateStatus(int reservationId, int merchantId, String status) throws Exception;
    public HashMap<String, Object> deleteReservation(int reservationId)throws Exception;
   public UpdateReservationResponse updateReservation(int reservationId, UpdateReservationRequest updateReservationRequest) throws Exception;
   public ReservationStatusResponse getReservationStatus(int reservationId) throws Exception;
   public List<ReservationResponse> getAllReservations(int merchantUserId)throws Exception;
}
