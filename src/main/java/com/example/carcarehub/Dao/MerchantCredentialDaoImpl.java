package com.example.carcarehub.Dao;

import com.example.carcarehub.domain.MerchantCredential;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
public class MerchantCredentialDaoImpl implements MerchantCredentialDao {

    @Autowired
    private EntityManager em;

    @Override
    @Transactional
    public MerchantCredential createMerchantCredential(MerchantCredential merchantCredential) {
        try {
            em.persist(merchantCredential);
            return merchantCredential;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
