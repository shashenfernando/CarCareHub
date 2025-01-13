package com.example.carcarehub.service;

import com.example.carcarehub.Dao.MerchantDao;
import com.example.carcarehub.Dao.ReservationDao;
import com.example.carcarehub.Dao.UserDao;
import com.example.carcarehub.domain.Merchant;
import com.example.carcarehub.domain.Reservation;
import com.example.carcarehub.domain.User;
import com.example.carcarehub.enums.CarCareHubException;
import com.example.carcarehub.enums.Status;
import com.example.carcarehub.model.request.ReservationRequest;
import com.example.carcarehub.model.request.UserRegistrationRequest;
import com.example.carcarehub.model.response.ReservationResponse;
import com.example.carcarehub.model.response.UserRegistrationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ReservationServiceImpl implements ReservationService{
    @Autowired
    UserDao userDao;
    @Autowired
    MerchantDao merchantDao;
    @Autowired
    ReservationDao reservationDao;
    @Override
    public ReservationResponse createReservation(ReservationRequest reservationRequest) throws Exception {

        User user = userDao.findUserById(reservationRequest.getUserId());

        if (user == null){
            throw new Exception(String.valueOf(CarCareHubException.USER_NOT_FOUND));
        }

        Merchant merchant = merchantDao.findMerchantById(reservationRequest.getMerchantId());

        if (merchant == null){
            throw new Exception(String.valueOf(CarCareHubException.MERCHANT_NOT_FOUND));
        }
        Random random = new Random();
        String reference = String.format("%04d", random.nextInt(9999));
        reference = reference+"car-care-hub";

        Reservation reservation = new Reservation();
        reservation.setUserId(reservation.getUserId());
        reservation.setUserEmail(reservationRequest.getUserEmail());
        reservation.setUserMobile(reservationRequest.getUserMobileNo());
        reservation.setReference(reference);
        reservation.setMerchantId(reservationRequest.getMerchantId());
        reservation.setMerchantEmail(reservationRequest.getMerchantEmail());
        reservation.setMerchantMobile(reservationRequest.getMerchantMobileNo());
        reservation.setStationName(reservationRequest.getStationName());
        reservation.setStatus(Status.PENDING_STATUS.getStatus());

        reservationDao.createReservation(reservation);

        ReservationResponse response = new ReservationResponse();
        response.setId(reservation.getId());
        response.setMerchantId(reservation.getMerchantId());
        response.setStationName(reservation.getStationName());
        response.setMerchantMobile(reservation.getMerchantMobile());
        response.setMerchantMobile(reservation.getMerchantMobile());
        response.setUserId(reservation.getUserId());
        response.setUserEmail(reservation.getUserEmail());
        response.setUserMobile(reservation.getUserMobile());
        response.setReference(reservation.getReference());
        response.setStatus(reservation.getStatus());
        return  response;
    }

    @Override
    public ReservationResponse getReservationById(int merchantId) throws Exception {

       Reservation reservation = reservationDao.findReservationById(merchantId);

       if (reservation == null){
           throw new Exception(String.valueOf(CarCareHubException.RESERVATION_NOT_FOUND));
       }
        ReservationResponse response = new ReservationResponse();
        response.setId(reservation.getId());
        response.setStatus(reservation.getStatus());
        response.setReference(reservation.getReference());
        response.setUserId(reservation.getUserId());
        response.setMerchantId(reservation.getMerchantId());

        return response;
    }
}
