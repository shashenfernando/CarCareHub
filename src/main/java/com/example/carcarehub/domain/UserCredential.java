package com.example.carcarehub.domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "user_credential")
public class UserCredential implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_name", nullable = false, length = 255)
    private String userName;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "status", length = 2)
    private String status = "I";  // default value 'I'

    @Column(name = "retry_count", nullable = false)
    private Integer retryCount = 0;  // Default value 0

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserCredential() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
