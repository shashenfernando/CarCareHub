package com.example.carcarehub.domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "merchant_credential")
public class MerchantCredential implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name", nullable = false, length = 255)
    private String user_name;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "status", length = 25)
    private String status = "I";  // Default value 'I'

    @Column(name = "retry_count", nullable = false)
    private Integer retryCount = 0;  // Default value 0

    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;

    public MerchantCredential() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }
}
