package com.example.carcarehub.Repository;

import com.example.carcarehub.domain.MerchantCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantCredentialRepo extends JpaRepository<MerchantCredential,Integer> {
    MerchantCredential findById(int merchantId);
}
