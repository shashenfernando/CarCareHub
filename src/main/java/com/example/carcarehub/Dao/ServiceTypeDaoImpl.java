package com.example.carcarehub.Dao;

import com.example.carcarehub.domain.ServiceType;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ServiceTypeDaoImpl implements ServiceTypeDao{

    private EntityManager em;
    private static final Logger logger = LoggerFactory.getLogger(ServiceTypeDaoImpl.class);

    @Override
    public ServiceType registerServiceType(ServiceType serviceType) {
        try {
            em.persist(serviceType);
            return serviceType;
        } catch (Exception e) {
            logger.error("Error registering service type", e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ServiceType findServiceTypeById(int serviceTypeId) {
        try {
            return em.find(ServiceType.class,serviceTypeId);
        } catch (Exception e) {
            return null;
        }
    }
}
