package com.example.carcarehub.service;

import java.util.HashMap;

public interface LoginService {
    public HashMap<String, Object> loginApplication(String email, String password, String role) throws Exception;
}
