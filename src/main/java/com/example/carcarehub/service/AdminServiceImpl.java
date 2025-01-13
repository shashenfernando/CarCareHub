package com.example.carcarehub.service;

import com.example.carcarehub.Dao.AdminCredentialDao;
import com.example.carcarehub.Dao.AdminDao;
import com.example.carcarehub.domain.Admin;
import com.example.carcarehub.domain.AdminCredential;
import com.example.carcarehub.enums.CarCareHubException;
import com.example.carcarehub.model.request.AdminAddRequest;
import com.example.carcarehub.model.request.AdminUpdateRequest;
import com.example.carcarehub.model.response.AdminResponse;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class AdminServiceImpl implements AdminService{

    @Autowired
    public AdminDao adminDao;

    @Autowired
    public AdminCredentialDao adminCredentialDao;

    @Override
    public List<AdminResponse> getAllAdmin() {
        List<Admin> adminList = adminDao.findAllAdmin();
        List<AdminResponse> adminResponse = new ArrayList<>();

        for (Admin admin: adminList){
            AdminResponse response = new AdminResponse();
            response.setId(admin.getId());
            response.setFirstName(admin.getFirstName());
            response.setLastName(admin.getLastName());
            response.setNicNumber(admin.getNic());
            response.setMobileNumber(admin.getMobileNumber());
            response.setEmail(admin.getEmail());
            response.setAddress(admin.getAddress());
            response.setRole(admin.getRole());
            response.setProfilePicture(admin.getProfilePicture());
            adminResponse.add(response);
        }
        return adminResponse;
    }

    @Override
    public Optional<AdminResponse> getAdminById(int id) {
        Optional<Admin> optionalAdmin = adminDao.findAdminById(id);

        if (optionalAdmin.isPresent()){
            Admin admin = optionalAdmin.get();
            AdminResponse adminResponse = new AdminResponse();
            adminResponse.setId(admin.getId());
            adminResponse.setFirstName(admin.getFirstName());
            adminResponse.setLastName(admin.getLastName());
            adminResponse.setNicNumber(admin.getNic());
            adminResponse.setMobileNumber(admin.getMobileNumber());
            adminResponse.setEmail(admin.getEmail());
            adminResponse.setAddress(admin.getAddress());
            adminResponse.setRole(admin.getRole());
            adminResponse.setProfilePicture(admin.getProfilePicture());
            return Optional.of(adminResponse);
        }else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<AdminResponse> getAdminByNicNumber(String nic) {
        Admin admin = adminDao.findAdminByNicNumber(nic);

        if (admin != null){
            AdminResponse adminResponse = new AdminResponse();
            adminResponse.setId(admin.getId());
            adminResponse.setFirstName(admin.getFirstName());
            adminResponse.setLastName(admin.getLastName());
            adminResponse.setNicNumber(admin.getNic());
            adminResponse.setMobileNumber(admin.getMobileNumber());
            adminResponse.setEmail(admin.getEmail());
            adminResponse.setAddress(admin.getAddress());
            adminResponse.setRole(admin.getRole());
            adminResponse.setProfilePicture(admin.getProfilePicture());
            return Optional.of(adminResponse);
        }else{
            return Optional.empty();
        }
    }

    @Override
    public Optional<AdminResponse> getAdminByUserName(String userName) {
        AdminCredential adminCredential = adminCredentialDao.findAdminByUserName(userName);

        if (adminCredential != null){
            Optional<Admin> optionalAdmin = adminDao.findAdminById(adminCredential.getAdmin().getId());
            Admin admin = optionalAdmin.get();
            AdminResponse adminResponse = new AdminResponse();
            adminResponse.setId(admin.getId());
            adminResponse.setFirstName(admin.getFirstName());
            adminResponse.setLastName(admin.getLastName());
            adminResponse.setNicNumber(admin.getNic());
            adminResponse.setMobileNumber(admin.getMobileNumber());
            adminResponse.setEmail(admin.getEmail());
            adminResponse.setAddress(admin.getAddress());
            adminResponse.setRole(admin.getRole());
            adminResponse.setProfilePicture(admin.getProfilePicture());
            return Optional.of(adminResponse);
        }else {
            return Optional.empty();
        }
    }
    private String hashedPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    @Override
    public Admin saveAdmin(AdminAddRequest adminAddRequest) throws Exception {

        AdminCredential adminCredential1 = adminCredentialDao.findAdminByUserName(adminAddRequest.getUserName());
        if(adminCredential1 != null){
            throw new Exception(String.valueOf(CarCareHubException.THIS_USER_NAME_ALREADY_EXIST));
        }

        Admin admin1 = adminDao.findAdminByNicNumber(adminAddRequest.getNic());
        if(admin1 != null){
            throw new Exception(String.valueOf(CarCareHubException.THIS_NIC_NUMBER_ALREADY_EXIST));
        }

        Admin admin3 = adminDao.findAdminByMobileNumber(adminAddRequest.getMobileNumber());
        if(admin3 != null){
            throw new Exception(String.valueOf(CarCareHubException.THIS_MOBILE_NUMBER_ALREADY_EXIST));
        }

        Admin admin4 = adminDao.findAdminByEmail(adminAddRequest.getEmail());
        if(admin4 != null){
            throw new Exception(String.valueOf(CarCareHubException.THIS_EMAIL_ALREADY_EXIST));
        }

        Admin admin = new Admin();
        Date date = new Date();
        AdminCredential adminCredential = new AdminCredential();
        String encryptedPassword = hashedPassword(adminAddRequest.getPassword());
        admin.setFirstName(adminAddRequest.getFirstName());
        admin.setLastName(adminAddRequest.getLastName());
        admin.setNic(adminAddRequest.getNic());
        admin.setEmail(adminAddRequest.getEmail());
        admin.setMobileNumber(adminAddRequest.getMobileNumber());
        admin.setAddress(adminAddRequest.getAddress());
        admin.setRole(adminAddRequest.getRole());
        admin.setProfilePicture(adminAddRequest.getProfilePicture());
        admin.setCreateDate(date);
        adminCredential.setAdmin(admin);
        adminCredential.setPassword(encryptedPassword);
        adminCredential.setUserName(adminAddRequest.getUserName());
        adminCredential.setLastLogin(adminAddRequest.getLastLogin());
        admin.setCreatedUser(adminAddRequest.getCreatedUser());
        adminDao.saveAdmin(admin);
        adminCredentialDao.saveAdminCredential(adminCredential);
        return admin;
    }

    @Override
    public AdminResponse updateAdmin(AdminUpdateRequest adminUpdateRequest, int id) throws Exception {

        Date date = new Date();

        Optional<Admin> optionalAdmin= adminDao.findAdminById(id);
        if (optionalAdmin.isPresent()){
            Admin admin = optionalAdmin.get();
            admin.setFirstName(adminUpdateRequest.getFirstName());
            admin.setLastName(adminUpdateRequest.getLastName());
            admin.setNic(adminUpdateRequest.getNic());
            admin.setEmail(adminUpdateRequest.getEmail());
            admin.setMobileNumber(adminUpdateRequest.getMobileNumber());
            admin.setRole(adminUpdateRequest.getRole());
            admin.setAddress(adminUpdateRequest.getAddress());
            admin.setModifiedUser(adminUpdateRequest.getModifiedUser());
            admin.setProfilePicture(adminUpdateRequest.getProfilePicture());
            admin.setModifiedDate(date);
            adminDao.saveAdmin(admin);

            AdminResponse adminResponse = new AdminResponse();
            adminResponse.setId(admin.getId());
            adminResponse.setFirstName(admin.getFirstName());
            adminResponse.setLastName(admin.getLastName());
            adminResponse.setNicNumber(admin.getNic());
            adminResponse.setMobileNumber(admin.getMobileNumber());
            adminResponse.setEmail(admin.getEmail());
            adminResponse.setAddress(admin.getAddress());
            adminResponse.setRole(admin.getRole());
            adminResponse.setProfilePicture(admin.getProfilePicture());
            return adminResponse;
        }else {
            throw new Exception(String.valueOf(CarCareHubException.ADMIN_NOT_FOUND));
        }
    }

}
