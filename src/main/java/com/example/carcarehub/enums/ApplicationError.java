package com.example.carcarehub.enums;

public enum ApplicationError {
    UNKNOWN_ERROR_OCCURED("001" , "Server error. Please try again later.");

    private String statusCode;
    private String statusMessage;

    private ApplicationError(String statusCode , String statusMessage){
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }
    public String statusCode(){
        return statusCode;
    }

    public String stausMessage(){
        return statusMessage;
    }
    }

}
