package com.example.carcarehub.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "admin")
public class Admin implements Serializable {

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
    @Column(name = "created_user", nullable = false)
    private String createdUser;
    @Column(name = "modified_date")
    private Date modifiedDate;
    @Column(name = "modified_user")
    private String modifiedUser;
    @Column(name = "profile_picture" , nullable = true)
    private String profilePicture;
    @Column(name = "address" , nullable = false, length = 255)
    private String address;
    @Column(name = "role" ,nullable = false, length = 255)
    private String role;
    @Column(name = "email" ,nullable = false, length = 255)
    private String email;

//----------------------------------------------------------------
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

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
