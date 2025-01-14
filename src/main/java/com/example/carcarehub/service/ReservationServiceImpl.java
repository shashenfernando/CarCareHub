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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        reference = "CARCAREHUB"+reference;

        Reservation reservation = new Reservation();
        reservation.setUserId(user.getId());
        reservation.setUserEmail(reservationRequest.getUserEmail());
        reservation.setUserMobile(reservationRequest.getUserMobileNo());
        reservation.setReference(reference);
        reservation.setMerchantId(reservationRequest.getMerchantId());
        reservation.setMerchantEmail(reservationRequest.getMerchantEmail());
        reservation.setMerchantMobile(reservationRequest.getMerchantMobileNo());
        reservation.setStationName(reservationRequest.getStationName());
        reservation.setStatus(Status.PENDING_STATUS.getStatus());
        reservation.setReservationDate(reservationRequest.getReservationDate());
        reservation.setReservationTime(reservationRequest.getReservationTime());
        reservation.setCreateDate(LocalDateTime.now());

        reservationDao.createReservation(reservation);

        ReservationResponse response = new ReservationResponse();
        response.setId(reservation.getId());
        response.setMerchantId(reservation.getMerchantId());
        response.setStationName(reservation.getStationName());
        response.setMerchantMobile(reservation.getMerchantMobile());
        response.setMerchantEmail(reservation.getMerchantEmail());
        response.setUserId(reservation.getUserId());
        response.setUserEmail(reservation.getUserEmail());
        response.setUserMobile(reservation.getUserMobile());
        response.setReference(reservation.getReference());
        response.setStatus(reservation.getStatus());
        response.setReservationTime(reservation.getReservationTime());
        response.setReservationDate(reservation.getReservationDate());
        response.setCreateDate(reservation.getCreateDate());
        return  response;
    }

    @Override
    public ReservationResponse getReservationById(int reservationId) throws Exception {

       Reservation reservation = reservationDao.findReservationById(reservationId);

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

    @Override
    public List<ReservationResponse> getPendingReservation(int merchantId) throws Exception {

        Merchant merchant = merchantDao.findMerchantById(merchantId);

        if (merchant == null){
            throw new Exception(String.valueOf(CarCareHubException.MERCHANT_NOT_FOUND));
        }

        List<ReservationResponse> reservationResponses = new ArrayList<>();

        List<Reservation> reservation = reservationDao.getReservationsById(merchantId);

        if (reservation !=null){

            for (Reservation reservation1 :reservation){
                ReservationResponse reservationResponse = new ReservationResponse();
                reservationResponse.setId(reservation1.getId());
                reservationResponse.setUserId(reservation1.getUserId());
                reservationResponse.setUserMobile(reservation1.getUserMobile());
                reservationResponse.setUserEmail(reservation1.getUserEmail());
                reservationResponse.setReference(reservation1.getReference());
                reservationResponse.setReservationDate(reservation1.getReservationDate());
                reservationResponse.setReservationTime(reservation1.getReservationTime());
                reservationResponses.add(reservationResponse);

            }
        }
        return reservationResponses;
    }

    @Override
    public HashMap<String, Object> acceptReservation(int merchantId, int reservationId) throws Exception {

       Merchant merchant = merchantDao.findMerchantById(merchantId);

       if (merchant == null){
           throw new Exception(String.valueOf(CarCareHubException.MERCHANT_NOT_FOUND));
       }
       Reservation reservation = reservationDao.findReservationById(reservationId);

        HashMap<String, Object> hm = new HashMap<String, Object>();

       if (reservation == null){
           throw new Exception(String.valueOf(CarCareHubException.RESERVATION_NOT_FOUND));
       }
       if (reservation.getStatus().equalsIgnoreCase(Status.PENDING_STATUS.getStatus())){
           reservation.setStatus(Status.ACCEPT_STATUS.getStatus());
       }
       if (!reservation.getStatus().equalsIgnoreCase(Status.ACCEPT_STATUS.getStatus())){
           hm.put("status", "success");
           hm.put("message", "Reservation Acceptance un-successful");
       }else {
           hm.put("status", "success");
           hm.put("message", "Reservation Acceptance successful");
       }
        return hm;
    }
}
