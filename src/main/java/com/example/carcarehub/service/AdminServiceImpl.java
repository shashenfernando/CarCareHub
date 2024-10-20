package com.example.carcarehub.service;

import com.example.carcarehub.domain.Admin;
import com.example.carcarehub.domain.AdminCredential;
import com.example.carcarehub.model.request.AdminAddRequest;
import com.example.carcarehub.model.response.AdminAddResponse;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;

@Service
public class AdminServiceImpl implements AdminService{
    @Override
    public AdminAddResponse saveAdmin(AdminAddRequest adminAddRequest) throws Exception {

        Admin admin = new Admin();
        Date date = new Date();
        AdminCredential adminCredential = new AdminCredential();

        admin.setFirstName(adminAddRequest.getFirstName());
        admin.setLastName(adminAddRequest.getLastName());
        admin.setNicNumber(adminAddRequest.getNicNumber());
        admin.setEmail(adminAddRequest.getEmail());
        admin.setContactNumber(adminAddRequest.getContactNumber());
        admin.setAddress(adminAddRequest.getAddress());
        admin.setRole(adminAddRequest.getRole());
        admin.setProfilePicture(adminAddRequest.getProfilePicture());
        adminCredential.setAdmin(admin);
        adminCredential.setPassword(adminAddRequest.getPassword());
        adminCredential.setUserName(adminAddRequest.getUserName());
        adminCredential.setLastLogin(adminAddRequest.getLastLogin());
        admin.setCreatedUser(adminAddRequest.getCreatedUser());


        return null;
    }
}
