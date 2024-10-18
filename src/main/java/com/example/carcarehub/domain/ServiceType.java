package com.example.carcarehub.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class ServiceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "service_type", nullable = false, length = 255)
    private String serviceType;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "status", nullable = false, length = 255)
    private String status;

    @Column(name = "price", nullable = false, length = 255)
    private String price;

    @Column(name = "average_time", nullable = false, length = 255)
    private Date averageTime;

    private int availableCount;

    @OneToOne
    @JoinColumn(name="vehicle_type_id")
    private VehicleType vehicleType;

    @OneToOne
    @JoinColumn(name="merchant_id")
    private Merchant merchant;


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}