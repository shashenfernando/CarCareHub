package com.example.carcarehub.Repository;

import com.example.carcarehub.domain.AdminCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminCredentialRepo extends JpaRepository<AdminCredential,Integer> {
    AdminCredential findById(int adminId);
}
