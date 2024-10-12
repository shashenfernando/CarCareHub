package com.example.carcarehub.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class DayOfWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String day;

    private String openTime;
    private String closeTime;
    @OneToOne(mappedBy="dayOfWeek")
    private Count count;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
