package com.example.carcarehub.Dao;

import com.example.carcarehub.domain.Admin;
import com.example.carcarehub.domain.AdminCredential;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AdminCredentialDao {

    AdminCredential findAdminByUserName(String userName);
    @Transactional
    AdminCredential saveAdminCredential(AdminCredential adminCredential);

}
