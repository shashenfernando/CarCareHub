package com.example.carcarehub.Dao;

import com.example.carcarehub.domain.Admin;
import com.example.carcarehub.domain.AdminCredential;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Override
    public AdminCredential findAdminByUserName(String userName) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<AdminCredential> query = cb.createQuery(AdminCredential.class);
            Root<AdminCredential> from = query.from(AdminCredential.class);

            query.select(from).where(cb.equal(from.get("userName"), userName));

            return (em.createQuery(query).getSingleResult());
        } catch (Exception e) {
            return null;
        }
    }
}
