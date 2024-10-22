//package com.example.carcarehub.domain;
//
//import javax.persistence.*;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.transaction.Transactional;
//
//import javax.persistence.Id;
//import javax.persistence.Table;
//import java.util.Date;
//
//
//@Table(name = "admin_credential")
//@Transactional
//@Entity
//public class AdminCredential {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private int id;
//
//    @Column(name = "user_Name", nullable = false, length = 25)
//    private String userName;
//
//    @Column(name = "password", nullable = false)
//    private String password;
//
//    @Column(name = "last_login")
//    private Date lastLogin;
//
//    @ManyToOne
//    @JoinColumn(name = "admin_id")
//    public Admin admin;
//
////------------------------------------------------------------
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public Date getLastLogin() {
//        return lastLogin;
//    }
//
//    public void setLastLogin(Date lastLogin) {
//        this.lastLogin = lastLogin;
//    }
//
//    public Admin getAdmin() {
//        return admin;
//    }
//
//    public void setAdmin(Admin admin) {
//        this.admin = admin;
//    }
//}
