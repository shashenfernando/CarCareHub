package com.example.carcarehub.Dao;

import com.example.carcarehub.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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

    @Override
    public User findUserByEmail(String email) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> query = cb.createQuery(User.class);
            Root<User> from = query.from(User.class);

            query.select(from).where(cb.equal(from.get("email"), email));

            return em.createQuery(query).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User findUserById(int userId) {
        try {
            return em.find(User.class, userId);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<User> getUserList() {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> query = cb.createQuery(User.class);
            Root<User> root = query.from(User.class);
            query.select(root);
            return em.createQuery(query).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User updateUser(User user) {

        try {
            em.merge(user);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public void deleteUser(User user) {
        em.remove(user);
    }
}
