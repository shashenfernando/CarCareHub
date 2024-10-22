package com.example.carcarehub.service;

import com.example.carcarehub.domain.Admin;
import com.example.carcarehub.model.request.AdminAddRequest;
import com.example.carcarehub.model.request.AdminUpdateRequest;
import com.example.carcarehub.model.response.AdminResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AdminService {

    /**
     * Get the all admin
     *
     * @return the all admin
     */
    List<AdminResponse> getAllAdmin();

    /**
     * Gets the admins by id
     *
     * @param id the id
     * @return Optional<AdminResponse>
     */
    Optional<AdminResponse> getAdminById(int id);

    /**
     * Gets the admins by id
     *
     * @param nic the nic
     * @return Optional<AdminResponse>
     */
    Optional<AdminResponse> getAdminByNicNumber(String nic);

    /**
     * Gets the admins by username
     *
     * @param userName the userName
     * @return Optional<Admin>
     */
    Optional<AdminResponse> getAdminByUserName(String userName);

    /**
     * Saves the given admin
     *
     * @param adminAddRequest the object to save
     * @return the saved admin
     */
    Admin saveAdmin(AdminAddRequest adminAddRequest) throws Exception;

    /**
     * Update the given admin
     *
     * @param adminUpdateRequest the object to save
     * @return the updated admin
     */
    AdminResponse updateAdmin(AdminUpdateRequest adminUpdateRequest, int id) throws Exception;

}
