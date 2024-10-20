package com.example.carcarehub.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUpdateRequest extends AdminAddRequest{
    private String ModifiedUser;

    public String getModifiedUser() {
        return ModifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        ModifiedUser = modifiedUser;
    }
}
