package com.example.carcarehub.Dao;

import com.example.carcarehub.domain.Merchant;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MerchantDaoImpl implements MerchantDao {
    @PersistenceContext
    EntityManager em;


    @Override
    public Merchant registerMerchant(Merchant merchant) {
        try {
            em.persist(merchant);
            return merchant;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
