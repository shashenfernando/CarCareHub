package com.example.carcarehub.service;

import com.example.carcarehub.Dao.MerchantDao;
import com.example.carcarehub.Dao.ReservationDao;
import com.example.carcarehub.Dao.UserDao;
import com.example.carcarehub.domain.EmergencyReservation;
import com.example.carcarehub.domain.Merchant;
import com.example.carcarehub.domain.Reservation;
import com.example.carcarehub.domain.User;
import com.example.carcarehub.enums.CarCareHubException;
import com.example.carcarehub.enums.Status;
import com.example.carcarehub.exception.AppException;
import com.example.carcarehub.model.request.EmergencyReservationRequest;
import com.example.carcarehub.model.request.ReservationRequest;
import com.example.carcarehub.model.request.UpdateReservationRequest;
import com.example.carcarehub.model.request.UserRegistrationRequest;
import com.example.carcarehub.model.response.*;
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
    @Autowired
    EmailSenderService emailSenderService;
    @Override
    public ReservationResponse createReservation(ReservationRequest reservationRequest) throws Exception {

        User user = userDao.findUserById(reservationRequest.getUserId());

        if (user == null){
            throw new AppException(CarCareHubException.USER_NOT_FOUND);
        }

        Merchant merchant = merchantDao.findMerchantById(reservationRequest.getMerchantId());

        if (merchant == null){
            throw new AppException(CarCareHubException.MERCHANT_NOT_FOUND);
        }

        int dailyCount = merchant.getDailyReservationLimit();
        Random random = new Random();
        String reference = String.format("%04d", random.nextInt(9999));
        reference = "CARCAREHUB"+reference;

        Reservation reservation = new Reservation();

        if (dailyCount == 0){
            throw new AppException(CarCareHubException.RESERVATION_COUNT_EXCEEDED);
        }

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
        reservation.setServiceType(reservationRequest.getServiceType());
        reservation.setVehicleType(reservationRequest.getVehicleType());

        reservationDao.createReservation(reservation);

        merchant.setDailyReservationLimit(merchant.getDailyReservationLimit()+1);

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
           throw new AppException(CarCareHubException.RESERVATION_NOT_FOUND);
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
            throw new AppException(CarCareHubException.MERCHANT_NOT_FOUND);
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
           throw new AppException(CarCareHubException.MERCHANT_NOT_FOUND);
       }
       Reservation reservation = reservationDao.findReservationById(reservationId);

        HashMap<String, Object> hm = new HashMap<String, Object>();

       if (reservation == null){
           throw new AppException(CarCareHubException.RESERVATION_NOT_FOUND);
       }
       if (reservation.getStatus().equalsIgnoreCase(Status.PENDING_STATUS.getStatus())){
           reservation.setStatus(Status.ACCEPT_STATUS.getStatus());
       }
       if (!reservation.getStatus().equalsIgnoreCase(Status.ACCEPT_STATUS.getStatus())){
           hm.put("status", "error");
           hm.put("message", "Reservation Acceptance un-successful");
       }else {
           hm.put("status", "success");
           hm.put("message", "Reservation Acceptance successful");
       }
        return hm;
    }

    @Override
    public ReservationStatusUpdate updateStatus(int reservationId, int merchantId, String status) throws Exception {

       Merchant merchant = merchantDao.findMerchantById(merchantId);

       if (merchant == null){
           throw new AppException(CarCareHubException.MERCHANT_NOT_FOUND);
       }
       Reservation reservation = reservationDao.findReservationById(reservationId);

       if (reservation == null){
           throw new AppException(CarCareHubException.RESERVATION_NOT_FOUND);
       }
       int userId = reservation.getUserId();

       User user = userDao.findUserById(userId);
       if (user == null){
           throw new AppException(CarCareHubException.USER_NOT_FOUND);
       }
       String to = user.getEmail();
       String from = null;
       String subject = null;
       String body = null;
       String merchantPhone = merchant.getBusinessMobileNumber();
       String merchantEmail = merchant.getBusinessEmail();
       String stationName = merchant.getStationName();
       String referenceNumber = reservation.getReference();

       if (status.equalsIgnoreCase(Status.ACCEPT_STATUS.getStatus())){
           reservation.setStatus(Status.ACCEPT_STATUS.getStatus());
           subject = "Your Reservation Has Been Accepted";
           body = "Dear Customer,\n\nYour reservation at " + stationName + " has been accepted.\n\nReservation Reference: " + referenceNumber + "\n\nThank you for choosing us!\n\nFor assistance, contact us at:\nPhone: " + merchantPhone + "\nEmail: " + merchantEmail + "\n\nBest regards,\n" + stationName;
           emailSenderService.sendEmail(to,from,subject,body);
       } else if (status.equalsIgnoreCase(Status.ON_HOLD.getStatus())) {
           reservation.setStatus(Status.ON_HOLD.getStatus());
           subject = "Your Reservation is On Hold";
           body = "Dear Customer,\n\nYour reservation at " + stationName + " is currently on hold.\n\nReservation Reference: " + referenceNumber + "\n\nWe will notify you of further updates.\n\nFor assistance, contact us at:\nPhone: " + merchantPhone + "\nEmail: " + merchantEmail + "\n\nBest regards,\n" + stationName;
           emailSenderService.sendEmail(to,from,subject,body);
       } else if (status.equalsIgnoreCase(Status.IN_PROGRESS.getStatus())) {
           reservation.setStatus(Status.IN_PROGRESS.getStatus());
           subject = "Your Reservation is In Progress";
           body = "Dear Customer,\n\nYour reservation at " + stationName + " is currently being processed.\n\nReservation Reference: " + referenceNumber + "\n\nWe appreciate your patience.\n\nFor assistance, contact us at:\nPhone: " + merchantPhone + "\nEmail: " + merchantEmail + "\n\nBest regards,\n" + stationName;
           emailSenderService.sendEmail(to,from,subject,body);
       } else if (status.equalsIgnoreCase(Status.COMPLETED.getStatus())) {
           reservation.setStatus(Status.COMPLETED.getStatus());
           subject = "Your Reservation is Completed";
           body = "Dear Customer,\n\nYour reservation at " + stationName + " has been successfully completed.\n\nReservation Reference: " + referenceNumber + "\n\nWe hope to see you again!\n\nFor assistance, contact us at:\nPhone: " + merchantPhone + "\nEmail: " + merchantEmail + "\n\nBest regards,\n" + stationName;
           emailSenderService.sendEmail(to,from,subject,body);
       } else if (status.equalsIgnoreCase(Status.RESCHEDULED.getStatus())) {
           reservation.setStatus(Status.RESCHEDULED.getStatus());
           subject = "Your Reservation Has Been Rescheduled";
           body = "Dear Customer,\n\nYour reservation at " + stationName + " has been rescheduled.\n\nReservation Reference: " + referenceNumber + "\n\nPlease check your new schedule.\n\nFor assistance, contact us at:\nPhone: " + merchantPhone + "\nEmail: " + merchantEmail + "\n\nBest regards,\n" + stationName;
           emailSenderService.sendEmail(to,from,subject,body);
       } else if (status.equalsIgnoreCase(Status.READY_FOR_PICKUP.getStatus())) {
           reservation.setStatus(Status.READY_FOR_PICKUP.getStatus());
           subject = "Your Reservation is Ready for Pickup";
           body = "Dear Customer,\n\nYour vehicle is now ready for pickup at " + stationName + ".\n\nReservation Reference: " + referenceNumber + "\n\nPlease visit our service center.\n\nFor assistance, contact us at:\nPhone: " + merchantPhone + "\nEmail: " + merchantEmail + "\n\nBest regards,\n" + stationName;
           emailSenderService.sendEmail(to,from,subject,body);
       } else if (status.equalsIgnoreCase(Status.CANCELED.getStatus())) {
           reservation.setStatus(Status.CANCELED.getStatus());
           subject = "Your Reservation Has Been Canceled";
           body = "Dear Customer,\n\nYour reservation at " + stationName + " has been canceled.\n\nReservation Reference: " + referenceNumber + "\n\nPlease contact us if you have any concerns.\n\nFor assistance, contact us at:\nPhone: " + merchantPhone + "\nEmail: " + merchantEmail + "\n\nBest regards,\n" + stationName;
           emailSenderService.sendEmail(to,from,subject,body);
       }
       else {
           throw new AppException(CarCareHubException.UNKNOWN_ERROR_OCCURED);
       }
        ReservationStatusUpdate reservationStatusUpdate = new ReservationStatusUpdate();

           reservationStatusUpdate.setReservationId(reservation.getId());
           reservationStatusUpdate.setMerchantId(reservation.getMerchantId());
           reservationStatusUpdate.setStatus(reservation.getStatus());

        return reservationStatusUpdate;
    }

    @Override
    public HashMap<String, Object> deleteReservation(int reservationId) throws Exception {

        Reservation reservation = reservationDao.findReservationById(reservationId);

        if (reservation == null){
            throw new AppException(CarCareHubException.RESERVATION_NOT_FOUND);
        }
        reservation.setStatus(Status.DECLINED_STATUS.getStatus());
        HashMap<String, Object> hm = new HashMap<String, Object>();

        if (reservation.getStatus().equalsIgnoreCase(Status.DECLINED_STATUS.getStatus())){
            hm.put("status","success");
            hm.put("message","reservation deleted");
        }
        else {
            hm.put("status", "error");
            hm.put("message", "reservation not deleted");
        }
        return hm;
    }

    @Override
    public UpdateReservationResponse updateReservation(int reservationId, UpdateReservationRequest updateReservationRequest) throws Exception {

       Reservation reservation = reservationDao.findReservationById(reservationId);

       if (reservation == null){
           throw new AppException(CarCareHubException.RESERVATION_NOT_FOUND);
       }

       Reservation newReservation = new Reservation();
       newReservation.setReservationTime(updateReservationRequest.getReservationTime());
       newReservation.setReservationDate(updateReservationRequest.getReservationDate());
       newReservation.setUserMobile(updateReservationRequest.getUserMobileNo());
       newReservation.setUserEmail(updateReservationRequest.getUserEmail());
       newReservation.setStationName(updateReservationRequest.getStationName());
       newReservation.setMerchantId(updateReservationRequest.getMerchantId());
       newReservation.setMerchantMobile(updateReservationRequest.getMerchantMobileNo());
       newReservation.setMerchantEmail(updateReservationRequest.getMerchantEmail());
       newReservation.setServiceType(updateReservationRequest.getServiceType());
       newReservation.setVehicleType(updateReservationRequest.getVehicleType());


        UpdateReservationResponse reservationResponse = new UpdateReservationResponse();

        reservationResponse.setUserId(reservation.getUserId());
        reservationResponse.setUserMobileNo(reservation.getUserMobile());
        reservationResponse.setUserEmail(reservation.getUserEmail());
        reservationResponse.setMerchantId(reservation.getMerchantId());
        reservationResponse.setMerchantEmail(reservation.getMerchantEmail());
        reservationResponse.setUserMobileNo(reservation.getMerchantMobile());
        reservationResponse.setReservationDate(reservation.getReservationDate());
        reservationResponse.setReservationTime(reservation.getReservationTime());

        return reservationResponse;
    }

    @Override
    public ReservationStatusResponse getReservationStatus(int reservationId) throws Exception {

        Reservation reservation = reservationDao.findReservationById(reservationId);

        if (reservation == null){
              throw new AppException(CarCareHubException.RESERVATION_NOT_FOUND);
        }
        ReservationStatusResponse response = new ReservationStatusResponse();
        response.setReservationId(reservation.getId());
        response.setStatus(reservation.getStatus());
        response.setMerchantId(reservation.getMerchantId());
        response.setUserId(reservation.getUserId());

        return response;
    }

    @Override
    public List<ReservationResponse> getAllReservations(int merchantUserId) throws Exception {

        Merchant merchant = merchantDao.findMerchantById(merchantUserId);

        if (merchant == null){
            throw new AppException(CarCareHubException.MERCHANT_NOT_FOUND);
        }

        List<ReservationResponse> reservationResponses = new ArrayList<>();

        List<Reservation> reservation = reservationDao.findReservationsById(merchantUserId);

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
    public ReservationResponse createEmergencyReservation(EmergencyReservationRequest reservationRequest) throws Exception {

        User user = userDao.findUserById(reservationRequest.getUserId());

        if (user == null){
            throw new AppException(CarCareHubException.USER_NOT_FOUND);
        }

        Merchant merchant = merchantDao.findMerchantById(reservationRequest.getMerchantId());

        if (merchant == null){
            throw new AppException(CarCareHubException.MERCHANT_NOT_FOUND);
        }

        int dailyCount = merchant.getDailyReservationLimit();
        Random random = new Random();
        String reference = String.format("%04d", random.nextInt(9999));
        reference = "CARCAREHUB EMERGENCY"+reference;

        EmergencyReservation reservation = new EmergencyReservation();

        if (dailyCount == 0){
            throw new AppException(CarCareHubException.RESERVATION_COUNT_EXCEEDED);
        }

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
        reservation.setServiceType("EMERGENCY");
        reservation.setVehicleType(reservationRequest.getVehicleType());

        reservationDao.createEmergencyReservation(reservation);

        merchant.setDailyReservationLimit(merchant.getDailyReservationLimit()+1);

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
}
