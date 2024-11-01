package com.example.carcarehub.Dao;

import com.example.carcarehub.domain.ServiceType;
import com.example.carcarehub.domain.User;

public interface ServiceTypeDao {
    public ServiceType registerServiceType(ServiceType serviceType);
    public ServiceType findServiceTypeById(int serviceTypeId);
}
