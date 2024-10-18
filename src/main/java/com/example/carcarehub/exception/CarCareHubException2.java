package com.example.carcarehub.exception;

import com.example.carcarehub.enums.ApplicationError;

public class CarCareHubException2 extends RuntimeException {

    private String errorCode;
    private String errorMessage;

    public CarCareHubException2 (ApplicationError applicationError) {
        this.errorCode = applicationError.statusCode();
        this.errorMessage = applicationError.stausMessage();
    }

    public CarCareHubException2(String errorCode, String errorMessage) {
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
