package com.example.carcarehub.Dao;

import com.example.carcarehub.domain.User;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    private EntityManager em;
    private static final Logger logger = LoggerFactory.getLogger(MerchantDaoImpl.class);

    @Override
    @Transactional
    public User registerUser(User user) {
        try {
            em.persist(user);
            return user;
        } catch (Exception e) {
            logger.error("Error registering user", e);
            e.printStackTrace();
            return null;
        }
    }
}
