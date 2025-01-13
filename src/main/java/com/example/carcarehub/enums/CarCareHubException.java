package com.example.carcarehub.enums;

public enum CarCareHubException {
    SUCCESS_REQUEST("000","Success"),
    UNKNOWN_ERROR_OCCURED("001" , "Server error. Please try again later."),
    MISSING_SOME_PARAMETERS("002", "Missing Some Parameters. Please Try Again."),
    EXISTING_MERCHANT_NAME("003","This name cannot be used. Because it already used." ),
    EXISTING_USER_NAME("004", "This  user name cannot be used. Because it already used."),
    USER_NOT_FOUND ("005","This user is not found"),
    DATA_NOT_FOUND ( "006" , "This data is not found"),
    INVALID_DATA  ("007" , "This data is invalid , please send correct data"),
    UNAUTHORIZED_REQUEST  ("008", "Unauthorized request"),
    BAD_REQUEST ( "009", "Bad request"),
    THIS_EMAIL_ALREADY_EXIST ("010","This email already exists"),
    NO_USERS_DATA_FOUND ("011","No users data found"),
    INVALID_USER ("012","Invalid user"),
    INVALID_PASSWORD ("013","Invalid password"),
    MERCHANT_NOT_FOUND ( "114","Merchant not found"),
    ADMIN_NOT_FOUND ( "115","Admin not found"),
    MERCHANT_DATA_NOT_FOUND ("116","Merchant data not found"),
    ADMIN_DATA_NOT_FOUND ("117","Admin data not found"),
    THIS_USER_NAME_ALREADY_EXIST ("118","This user name already exists"),
    THIS_NIC_NUMBER_ALREADY_EXIST ("119","This nic number already exists"),
    THIS_MOBILE_NUMBER_ALREADY_EXIST ("120","This mobile number already exists"),
    SERVICE_TYPE_NOT_FOUND ( "121","This service type not found"),
    EMAIL_AND_PASSWORD_MANDATORY ("121","Email and password is required");



    private String statusCode;
    private String statusMessage;

    private CarCareHubException(String statusCode , String statusMessage){
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


