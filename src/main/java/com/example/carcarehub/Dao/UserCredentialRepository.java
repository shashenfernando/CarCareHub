package com.example.carcarehub.Dao;

import com.example.carcarehub.domain.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential ,Integer> {


}
