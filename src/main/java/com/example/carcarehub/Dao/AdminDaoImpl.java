package com.example.carcarehub.Dao;

import com.example.carcarehub.domain.Admin;
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
import java.util.Optional;

@Repository
public class AdminDaoImpl implements AdminDao{

    @Autowired
    private EntityManager em;
    private static final Logger logger = LoggerFactory.getLogger(AdminDaoImpl.class);

    @Override
    @Transactional
    public Admin saveAdmin(Admin admin) {
        try {
            em.persist(admin);
            return admin;
        } catch (Exception e) {
            logger.error("Error registering admin", e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Admin> findAdminById(int id) {
        try {
            return Optional.ofNullable(em.find(Admin.class, id));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Admin findAdminByMobileNumber(String mobileNumber) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Admin> query = cb.createQuery(Admin.class);
            Root<Admin> from = query.from(Admin.class);

            query.select(from).where(cb.equal(from.get("mobileNumber"), mobileNumber));

            return (em.createQuery(query).getSingleResult());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Admin findAdminByNicNumber(String nic) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Admin> query = cb.createQuery(Admin.class);
            Root<Admin> from = query.from(Admin.class);

            query.select(from).where(cb.equal(from.get("nic"), nic));

            return (em.createQuery(query).getSingleResult());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Admin findAdminByEmail(String email) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Admin> query = cb.createQuery(Admin.class);
            Root<Admin> from = query.from(Admin.class);

            query.select(from).where(cb.equal(from.get("email"), email));

            return (em.createQuery(query).getSingleResult());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Admin> findAllAdmin() {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Admin> query = cb.createQuery(Admin.class);
            Root<Admin> root = query.from(Admin.class);
            query.select(root);
            return em.createQuery(query).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Admin updateUser(Admin admin) {

        try {
            em.merge(admin);
            return admin;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
