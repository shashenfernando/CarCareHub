package com.example.carcarehub.controller;

import com.example.carcarehub.domain.Admin;
import com.example.carcarehub.enums.CarCareHubException;
import com.example.carcarehub.exception.AppException;
import com.example.carcarehub.model.request.AdminAddRequest;
import com.example.carcarehub.model.request.AdminUpdateRequest;
import com.example.carcarehub.model.response.AdminResponse;
import com.example.carcarehub.model.response.CarCareHubResponse;
import com.example.carcarehub.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/admin")
public class AdminController {

    @Autowired
    private AdminService adminService ;

    /**
     * Gets the all admins
     *
     * @return List<AdminResponse>
     */
    @GetMapping(value = "/all")
    public CarCareHubResponse getAllAdmin() throws Exception {
        List<AdminResponse> admins = adminService.getAllAdmin();
        CarCareHubResponse response = new CarCareHubResponse();
        response.setResponseCode("00");
        response.setResponseObject(admins);
        if (response.getResponseObject() != null){
            return response;
        }else{
            throw new AppException(CarCareHubException.ADMIN_NOT_FOUND);
        }
    }

    /**
     * Gets the admins by id
     *
     * @param id the id
     * @return Optional<AdminResponse>
     */
    @GetMapping(value = "/{id}")
    public CarCareHubResponse getAdminById(@PathVariable int id) throws Exception {
        Optional<AdminResponse> optionalAdmin = adminService.getAdminById(id);
        if (optionalAdmin.isPresent()){
            CarCareHubResponse response = new CarCareHubResponse();
            response.setResponseCode("00");
            response.setResponseObject(optionalAdmin);
            return response;
        }else{
            throw new AppException(CarCareHubException.ADMIN_NOT_FOUND);
        }
    }

    /**
     * Gets the admins by id
     *
     * @param nic the nic
     * @return Optional<AdminResponse>
     */
    @GetMapping(value = "/nicNumber/{nic}")
    public CarCareHubResponse getAdminByNicNumber(@PathVariable String nic) throws Exception {
        Optional<AdminResponse> optionalAdmin = adminService.getAdminByNicNumber(nic);
        if (optionalAdmin.isPresent()){
            CarCareHubResponse response = new CarCareHubResponse();
            response.setResponseCode("00");
            response.setResponseObject(optionalAdmin);
            return response;
        }else{
            throw new AppException(CarCareHubException.ADMIN_NOT_FOUND);
        }
    }

    /**
     * Gets the admins by username
     *
     * @param userName the userName
     * @return Optional<Admin>
     */
    @GetMapping(value = "/userName/{userName}")
    public CarCareHubResponse getAdminByUserName(@PathVariable String userName) throws Exception {
        Optional<AdminResponse> optionalAdmin = adminService.getAdminByUserName(userName);
        if (optionalAdmin.isPresent()){
            CarCareHubResponse response = new CarCareHubResponse();
            response.setResponseCode("00");
            response.setResponseObject(optionalAdmin);
            return response;
        }else{
            throw new AppException(CarCareHubException.ADMIN_NOT_FOUND);
        }
    }

    /**
     * Saves the given admin
     *
     * @param adminAddRequest the object to save
     * @return the saved admin
     */
    @PostMapping(value = "/save")
    public CarCareHubResponse saveAdmin(@RequestBody AdminAddRequest adminAddRequest) throws Exception{

        Admin newAdmin = adminService.saveAdmin(adminAddRequest);
        CarCareHubResponse response = new CarCareHubResponse();
        response.setResponseCode("000");
        response.setResponseObject(newAdmin);
        return response;
    }

    /**
     * Update the given admin
     *
     * @param adminUpdateRequest the object to save
     * @return the updated admin
     */
    @PutMapping(value = "/update/{id}")
    public CarCareHubResponse updateAdmin(@RequestBody AdminUpdateRequest adminUpdateRequest,
                                          @PathVariable int id) throws Exception{

        Optional<AdminResponse> optionalAdmin = adminService.getAdminById(id);
        if (optionalAdmin.isPresent()){
            AdminResponse adminResponse = adminService.updateAdmin(adminUpdateRequest, id);
            CarCareHubResponse response = new CarCareHubResponse();
            response.setResponseCode("000");
            response.setResponseObject(adminResponse);
            return response;
        }
        throw new AppException(CarCareHubException.ADMIN_NOT_FOUND);
    }

}
