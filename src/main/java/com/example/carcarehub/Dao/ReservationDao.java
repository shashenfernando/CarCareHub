package com.example.carcarehub.Dao;

import com.example.carcarehub.domain.Reservation;
import org.springframework.transaction.annotation.Transactional;

public interface ReservationDao {
    @Transactional
    Reservation createReservation(Reservation reservation);
}
