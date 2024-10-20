package com.example.carcarehub.service.impl;

import com.example.carcarehub.Dao.AdminCredentialDao;
import com.example.carcarehub.Dao.AdminDao;
import com.example.carcarehub.domain.Admin;
import com.example.carcarehub.domain.AdminCredential;
import com.example.carcarehub.model.request.AdminAddRequest;
import com.example.carcarehub.model.request.AdminUpdateRequest;
import com.example.carcarehub.model.response.AdminAddResponse;
import com.example.carcarehub.service.AdminService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Component
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private AdminCredentialDao adminCredentialDao;

    @Override
    public List<Admin> getAllAdmin() {
        return adminDao.findAllAdmin();
    }

    @Override
    public Optional<Admin> getAdminById(Long id) {
        return adminDao.findAdminById(id);
    }

    @Override
    public AdminAddResponse saveAdmin(AdminAddRequest adminAddRequest) {

        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();

//        Optional<Admin> optionalAdmin1 = adminDao.findAdminByNicNumber(adminAddRequest.getNicNumber());
//        if (optionalAdmin1.isPresent())
//            throw new RuntimeException("Nic number cannot be duplicated");
//
//        Optional<Admin> optionalAdmin2 = adminDao.findAdminByUserName(adminAddRequest.getUserName());
//        if (optionalAdmin2.isPresent())
//            throw new RuntimeException("User name cannot be duplicated");

        Admin admin = new Admin();
        AdminCredential adminCredential = new AdminCredential();

//        admin.setId(1L);
        admin.setFirstName(adminAddRequest.getFirstName());
        admin.setLastName(adminAddRequest.getLastName());
        admin.setNicNumber(adminAddRequest.getNicNumber());
        admin.setEmail(adminAddRequest.getEmail());
        admin.setContactNumber(adminAddRequest.getContactNumber());
        admin.setAddress(adminAddRequest.getAddress());
        admin.setRole(adminAddRequest.getRole());
        admin.setProfilePicture(adminAddRequest.getProfilePicture());
        admin.setCreatedUser(adminAddRequest.getCreatedUser());
        admin.setCreatedDate(currentDate);
//        adminCredential.setId(1L);
        adminCredential.setUserName(adminAddRequest.getUserName());

        String hashedPassword = hashedPassword(adminAddRequest.getPassword());
        adminCredential.setPassword(hashedPassword);
        adminCredential.setAdmin(admin);
        adminCredential.setLastLogin(currentDate);
        adminCredentialDao.saveAdminCredential(adminCredential);
        adminDao.saveAdmin(admin);

        AdminAddResponse response = new AdminAddResponse();
        response.setId(admin.getId());
        response.setFirstName(admin.getFirstName());
        response.setLastName(admin.getLastName());
        response.setNicNumber(admin.getNicNumber());
        response.setEmail(admin.getEmail());
        response.setContactNumber(admin.getContactNumber());
        response.setRole(admin.getRole());
        response.setAddress(admin.getAddress());
        response.setProfilePicture(admin.getProfilePicture());
        return response;
    }

    @Override
    public Admin updateAdmin(AdminUpdateRequest adminUpdateRequest, Long id){
        Optional<Admin> optionalAdmin = adminDao.findAdminById(id);

        if (optionalAdmin.isPresent()){
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();

            Admin admin = new Admin();

            admin.setFirstName(adminUpdateRequest.getFirstName());
            admin.setLastName(adminUpdateRequest.getLastName());
            admin.setNicNumber(adminUpdateRequest.getNicNumber());
            admin.setEmail(adminUpdateRequest.getEmail());
            admin.setContactNumber(adminUpdateRequest.getContactNumber());
            admin.setAddress(adminUpdateRequest.getAddress());
            admin.setRole(adminUpdateRequest.getRole());
            admin.setProfilePicture(adminUpdateRequest.getProfilePicture());
            admin.setModifiedUser(adminUpdateRequest.getModifiedUser());
            admin.setModifiedDate(currentDate);
            adminDao.saveAdmin(admin);
            return admin;
        }else {
            throw new RuntimeException("Record not found.");
        }
    }

    private String hashedPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
