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
    public void saveAdmin(Admin admin) {
        try {
            em.persist(admin);
        } catch (Exception e) {
            logger.error("Error registering user", e);
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Admin> findAdminById(Long id) {
        try {
            return Optional.ofNullable(em.find(Admin.class, id));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Optional<Admin> findAdminByUserName(String userName) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Admin> query = cb.createQuery(Admin.class);
            Root<Admin> from = query.from(Admin.class);

            query.select(from).where(cb.equal(from.get("userName"), userName));

            return Optional.ofNullable(em.createQuery(query).getSingleResult());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Optional<Admin> findAdminByNicNumber(String nicNumber) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Admin> query = cb.createQuery(Admin.class);
            Root<Admin> from = query.from(Admin.class);

            query.select(from).where(cb.equal(from.get("nicNumber"), nicNumber));

            return Optional.ofNullable(em.createQuery(query).getSingleResult());
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
