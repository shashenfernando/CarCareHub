package com.example.carcarehub.controller;
import com.example.carcarehub.domain.ServiceType;
import com.example.carcarehub.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-types")
public class ServiceTypeController {

    private final ServiceTypeService serviceTypeService;

    @Autowired
    public ServiceTypeController(ServiceTypeService serviceTypeService) {
        this.serviceTypeService = serviceTypeService;
    }

    @GetMapping
    public List<ServiceType> getAllServiceTypes() {
        return serviceTypeService.getAllServiceTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceType> getServiceTypeById(@PathVariable Integer id) {
        ServiceType serviceType = serviceTypeService.getServiceTypeById(id);
        return serviceType != null ? ResponseEntity.ok(serviceType) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ServiceType> createServiceType(@RequestBody ServiceType serviceType) {
        return ResponseEntity.ok(serviceTypeService.createServiceType(serviceType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceType> updateServiceType(@PathVariable Integer id, @RequestBody ServiceType updatedServiceType) {
        ServiceType serviceType = serviceTypeService.updateServiceType(id, updatedServiceType);
        return serviceType != null ? ResponseEntity.ok(serviceType) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceType(@PathVariable Integer id) {
        boolean deleted = serviceTypeService.deleteServiceType(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
