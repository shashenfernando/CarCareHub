package com.example.carcarehub.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "merchant")
public class Merchant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name="merchant_credentials_id")
    private MerchantCredential merchantCredential;
    @Column(name = "station_name", nullable = false, length = 255)
    private String stationName;
    @Column(name = "business_reg_no", nullable = false, length = 255)
    private String businessRegNo;
    @Column(name = "email", nullable = false, length = 255)
    private String businessEmail;

    @Column(name = "mobile_number", nullable = false, length = 255)
    private String businessMobileNumber;

    @Column(name = "register_date", nullable = false)
    private Date registeredDate;

    @Column(name = "approved_by", length = 255)
    private String approvedBy;

    @Column(name = "approved_date", length = 255)
    private Date approvedDate;

    @Column(name = "zip_code", nullable = false, length = 255)
    private String zipCode;

    @Column(name = "longitude", nullable = false, length = 255)
    private String longitude;

    @Column(name = "latitude", nullable = false, length = 255)
    private String latitude;

    @Column(name = "road", nullable = false, length = 255)
    private String road;

    @Column(name = "home_town", nullable = false, length = 255)
    private String homeTown;

    @Column(name = "status", nullable = false)
    private String status = "I";


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MerchantCredential getMerchantCredential() {
        return merchantCredential;
    }

    public void setMerchantCredential(MerchantCredential merchantCredential) {
        this.merchantCredential = merchantCredential;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }


    public String getBusinessRegNo() {
        return businessRegNo;
    }

    public void setBusinessRegNo(String businessRegNo) {
        this.businessRegNo = businessRegNo;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

    public String getBusinessMobileNumber() {
        return businessMobileNumber;
    }

    public void setBusinessMobileNumber(String businessMobileNumber) {
        this.businessMobileNumber = businessMobileNumber;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
