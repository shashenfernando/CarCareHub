package com.example.carcarehub.enums;

public enum ApplicationError {
    UNKNOWN_ERROR_OCCURED("001" , "Server error. Please try again later."),
    MISSING_SOME_PARAMETERS("002", "Missing Some Parameters. Please Try Again."),
    EXISTING_MERCHANT_NAME("003","This name cannot be used. Because it already used." ),
    EXISTING_USER_NAME("004", "This  user name cannot be used. Because it already used.");

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


