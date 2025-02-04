package com.example.carcarehub.exception;

import com.example.carcarehub.enums.CarCareHubException;

public class AppException extends RuntimeException {

    private String errorCode;
    private String errorMessage;

    public AppException(CarCareHubException applicationError) {
        this.errorCode = applicationError.statusCode();
        this.errorMessage = applicationError.statusMessage();
    }

    public AppException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
