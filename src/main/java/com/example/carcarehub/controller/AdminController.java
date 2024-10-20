package com.example.carcarehub.controller;

import com.example.carcarehub.domain.Admin;
import com.example.carcarehub.model.request.AdminAddRequest;
import com.example.carcarehub.model.request.AdminUpdateRequest;
import com.example.carcarehub.model.response.AdminAddResponse;
import com.example.carcarehub.model.response.CarCareHubResponse;
import com.example.carcarehub.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService ;

    /**
     * Gets the all admins
     *
     * @return List<Admin>
     */
    @GetMapping(value = "/all")
    public ResponseEntity<Object> getAllAdmin(){
        List<Admin> admins = adminService.getAllAdmin();
        if (admins != null && !admins.isEmpty()){
            return new ResponseEntity<>(admins, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Gets the admins by id
     *
     * @param id the id
     * @return Optional<Admin>
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getAdminById(@PathVariable Long id){
        Optional<Admin> optionalAdmin = adminService.getAdminById(id);
        if (optionalAdmin.isPresent()){
            return new ResponseEntity<>(optionalAdmin, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Saves the given admin
     *
     * @param adminAddRequest the object to save
     * @return the saved admin
     */
    @RequestMapping(method = RequestMethod.POST , value ="/save")
    public CarCareHubResponse saveAdmin(@RequestBody AdminAddRequest adminAddRequest){

        AdminAddResponse newAdmin = adminService.saveAdmin(adminAddRequest);
        CarCareHubResponse response = new CarCareHubResponse();
        response.setResponseCode("11");
        response.setResponseObject(newAdmin);
        return response;
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Object> updateAdmin(@RequestBody AdminUpdateRequest adminUpdateRequest, @PathVariable Long id){

        Optional<Admin> optionalAdmin = adminService.getAdminById(id);
        if (optionalAdmin.isPresent()){
            Admin newAdmin = adminService.updateAdmin(adminUpdateRequest, id);
            return new ResponseEntity<>(newAdmin, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
