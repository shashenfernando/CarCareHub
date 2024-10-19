package com.example.carcarehub.domain;

import jakarta.persistence.*;

@Entity
public class Count {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int dailyCount;
    private int availableCount;

    @OneToOne
    @JoinColumn(name="date_id")
    private DayOfWeek dayOfWeek;

    @OneToOne
    @JoinColumn(name="merchant_id")
    private Merchant merchant;

    @OneToOne
    @JoinColumn(name="service_type_id")
    private ServiceType service;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getDailyCount() {
        return dailyCount;
    }

    public void setDailyCount(int dailyCount) {
        this.dailyCount = dailyCount;
    }

    public int getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public ServiceType getService() {
        return service;
    }

    public void setService(ServiceType service) {
        this.service = service;
    }
}
