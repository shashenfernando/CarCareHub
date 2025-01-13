package com.example.carcarehub.Repository;

import com.example.carcarehub.domain.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialRepo extends JpaRepository<UserCredential,Integer> {
    UserCredential findById(int userId);
}
