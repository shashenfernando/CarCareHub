package com.example.carcarehub.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name" , nullable = false, length = 255)
    private String firstName;
    @Column(name = "last_name" ,nullable = false, length = 255)
    private String lastName;
    @Column(name = "nic" ,nullable = false, length = 255)
    private String nic;
    @Column(name = "mobile_number" , nullable = false, length = 255)
    private String mobileNumber;
    @Column(name = "create_date" , nullable = false)
    private Date createDate;
    @Column(name = "zip_code" , nullable = false)
    private String zipCode;
    @Column(name = "longitude" , nullable = false, length = 255)
    private String longitude;
    @Column(name = "latitude" , nullable = false, length = 255)
    private String latitude;
    @Column(name = "home_town" , nullable = false, length = 255)
    private String homeTown;
    @Column(name = "road" ,nullable = false, length = 255)
    private String road;
    @Column(name = "email" ,nullable = false, length = 255)
    private String email;
    @OneToOne
    @JoinColumn(name="user_credentials_id")
    private UserCredential userCredential;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserCredential getUserCredential() {
        return userCredential;
    }

    public void setUserCredential(UserCredential userCredential) {
        this.userCredential = userCredential;
    }
}
