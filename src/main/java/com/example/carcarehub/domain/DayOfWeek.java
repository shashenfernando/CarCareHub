package com.example.carcarehub.domain;

import jakarta.persistence.*;

@Entity
public class DayOfWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String day;
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
