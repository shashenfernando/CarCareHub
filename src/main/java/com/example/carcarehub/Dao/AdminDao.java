package com.example.carcarehub.Dao;

import com.example.carcarehub.domain.Admin;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminDao{

    List<Admin> findAllAdmin();

    Optional<Admin> findAdminById(int id);

    Admin findAdminByMobileNumber(String mobileNumber);

    Admin findAdminByNicNumber(String nic);

    Admin findAdminByEmail(String email);

    @Transactional
    Admin saveAdmin(Admin admin);

    @Transactional
    Admin updateUser(Admin admin);
}
