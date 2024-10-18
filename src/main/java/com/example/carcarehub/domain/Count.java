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
}
