package com.example.carcarehub.enums;

public enum Status {
    PENDING_STATUS("P","PENDING"),
    APPROVED_STATUS("A","APPROVED"),
    DECLINED_STATUS("D","DECLINED"),;


    private String status;
    private String description;

    Status(String status, String description) {
        this.status = status;
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
