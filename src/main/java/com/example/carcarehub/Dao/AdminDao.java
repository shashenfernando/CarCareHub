//package com.example.carcarehub.Dao;
//
//import com.example.carcarehub.domain.Admin;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface AdminDao{
//
//    List<Admin> findAllAdmin();
//
//    Optional<Admin> findAdminById(Long id);
//
//    Optional<Admin> findAdminByUserName(String userName);
//
//    Optional<Admin> findAdminByNicNumber(String nicNumber);
//
//    @Transactional
//    Admin saveAdmin(Admin admin);
//
//    @Transactional
//    Admin updateUser(Admin admin);
//}
