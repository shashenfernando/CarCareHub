package com.example.carcarehub.domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "merchant_credential")
public class MerchantCredential implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "station_name", nullable = false, length = 255)
    private String stationName;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "status", length = 25)
    private String status = "I";  // Default value 'I'

    @Column(name = "retry_count", nullable = false)
    private Integer retryCount = 0;  // Default value 0

    public MerchantCredential() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public MerchantCredential(Integer id, String stationName, String password, String status, Integer retryCount) {
        this.id = id;
        this.stationName = stationName;
        this.password = password;
        this.status = status;
        this.retryCount = retryCount;
    }

    @Override
    public String toString() {
        return "MerchantCredential{" +
                "id=" + id +
                ", stationName='" + stationName + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", retryCount=" + retryCount +
                '}';
    }
}
