package com.example.carcarehub.Dao;

import com.example.carcarehub.domain.User;
import com.example.carcarehub.domain.UserCredential;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserCredentialDaoImpl implements UserCredentialDao {
    @Autowired
    private EntityManager em;
    private static final Logger logger = LoggerFactory.getLogger(MerchantDaoImpl.class);
    @Override
    @Transactional
    public UserCredential createUserCredential(UserCredential userCredential) {
        try {
            em.persist(userCredential);
            return userCredential;
        } catch (Exception e) {
            logger.error("Error create user credentials", e);
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public void deleteUserCredentials(User user) {
        em.createQuery("DELETE FROM UserCredential uc WHERE uc.user = :user")
                .setParameter("user", user)
                .executeUpdate();
    }
}
