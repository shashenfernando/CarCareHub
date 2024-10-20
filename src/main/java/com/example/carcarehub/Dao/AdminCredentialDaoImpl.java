package com.example.carcarehub.Dao;

import com.example.carcarehub.domain.AdminCredential;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AdminCredentialDaoImpl implements AdminCredentialDao {

    @Autowired
    private EntityManager em;
    private static final Logger logger = LoggerFactory.getLogger(AdminCredentialDaoImpl.class);

    @Override
    @Transactional
    public AdminCredential saveAdminCredential(AdminCredential adminCredential) {
        try {
            em.persist(adminCredential);
            return adminCredential;
        } catch (Exception e) {
            logger.error("Error registering user", e);
            e.printStackTrace();
            return null;
        }
    }
}
