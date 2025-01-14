package com.example.carcarehub.Dao;

import com.example.carcarehub.domain.Reservation;
import com.example.carcarehub.enums.Status;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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
public class ReservationDaoImpl implements ReservationDao{

    @Autowired
    private EntityManager em;
    private static final Logger logger = LoggerFactory.getLogger(MerchantDaoImpl.class);
    @Override
    @Transactional
    public Reservation createReservation(Reservation reservation) {
        try {
            em.persist(reservation);
            return reservation;
        } catch (Exception e) {
            logger.error("Error registering user", e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Reservation findReservationById(int id) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Reservation> query = cb.createQuery(Reservation.class);
            Root<Reservation> from = query.from(Reservation.class);

            query.select(from).where(cb.equal(from.get("id"), id));

            return em.createQuery(query).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Reservation findReservationByMerchantId(int merchantId) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Reservation> query = cb.createQuery(Reservation.class);
            Root<Reservation> from = query.from(Reservation.class);

            query.select(from).where(cb.equal(from.get("merchantId"), merchantId));

            return em.createQuery(query).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Reservation> getReservationsById(int merchantId) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Reservation> query = cb.createQuery(Reservation.class);
            Root<Reservation> reservationRoot = query.from(Reservation.class);

            query.select(reservationRoot).where(cb.equal(reservationRoot.get("merchantId"), merchantId),
                    cb.equal(reservationRoot.get("is_active"), "A"));

            TypedQuery<Reservation> typedQuery = em.createQuery(query);
            return typedQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
