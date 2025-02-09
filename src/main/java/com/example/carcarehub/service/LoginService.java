package com.example.carcarehub.service;

import com.example.carcarehub.model.request.resetPasswordResetRequest;

import java.util.HashMap;

public interface LoginService {
    public HashMap<String, Object> loginApplication(String email, String password, String role) throws Exception;
    public HashMap<String, Object> resetPasswordReset(int userId, resetPasswordResetRequest request) throws Exception;
}
