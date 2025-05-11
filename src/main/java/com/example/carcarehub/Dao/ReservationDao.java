package com.example.carcarehub.Dao;

import com.example.carcarehub.domain.EmergencyReservation;
import com.example.carcarehub.domain.Reservation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReservationDao {
    @Transactional
    Reservation createReservation(Reservation reservation);
    Reservation findReservationById(int id);
    Reservation findReservationByMerchantId(int merchantId);
    public List<Reservation>getReservationsById(int merchantId);
   public List<Reservation> findReservationsById(int merchantId);
   public EmergencyReservation createEmergencyReservation(EmergencyReservation reservation);
}
