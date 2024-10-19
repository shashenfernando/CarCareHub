package com.example.carcarehub.Dao;

import com.example.carcarehub.domain.Merchant;
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

    @Override
    public MerchantCredential findByUserName(String userName) {
            try {
                return em.createQuery("SELECT mc FROM MerchantCredential mc WHERE mc.user_name = :userName", MerchantCredential.class)
                        .setParameter("userName", userName)
                        .getSingleResult();
            } catch (Exception e) {
                return null;
            }
    }

    @Override
    public void deleteMerchantCredentials(Merchant merchant) {
        em.createQuery("DELETE FROM MerchantCredential mc WHERE mc.merchant = :merchant")
                .setParameter("merchant", merchant)
                .executeUpdate();
    }
}
