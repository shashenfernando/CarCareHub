package com.example.carcarehub.repository;

import com.example.carcarehub.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

 User findUserByEmail(String email);
 String existsByEmail(String email);
 User findUserById(int userId);
 User deleteUserById(int Id);



}
