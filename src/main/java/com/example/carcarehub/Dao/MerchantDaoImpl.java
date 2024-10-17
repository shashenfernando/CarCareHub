package com.example.carcarehub.Dao;

import com.example.carcarehub.domain.Merchant;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;

@Repository
public class MerchantDaoImpl implements MerchantDao {

    @Autowired
    private EntityManager em;

    private static final Logger logger = LoggerFactory.getLogger(MerchantDaoImpl.class);

    @Transactional
    @Override
    public Merchant registerMerchant(Merchant merchant) {
        merchant.setRegisteredDate(new Date());
        try {
            em.persist(merchant);
            return merchant;
        } catch (Exception e) {
            logger.error("Error registering merchant", e);
            e.printStackTrace();
            return null;
        }
    }

}
