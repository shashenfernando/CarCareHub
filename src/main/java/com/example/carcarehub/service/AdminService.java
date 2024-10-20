package com.example.carcarehub.service;

import com.example.carcarehub.domain.Admin;
import com.example.carcarehub.model.request.AdminAddRequest;
import com.example.carcarehub.model.request.AdminUpdateRequest;
import com.example.carcarehub.model.response.AdminAddResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AdminService {

    /**
     * Gets the all admins
     *
     * @return the all admins
     */
    List<Admin> getAllAdmin();

    /**
     * Gets the admins by id
     *
     * @param id the id
     * @return the admin
     */
    Optional<Admin> getAdminById(Long id);

    /**
     * Saves the given admin
     *
     * @param adminResource the object to save
     * @return the saved admin
     */
    AdminAddResponse saveAdmin(AdminAddRequest adminResource);

    /**
     * Updates the admin object by given object
     *
     * @return the updated admin object
     */
    Admin updateAdmin(AdminUpdateRequest adminUpdateRequest, Long id);
}
