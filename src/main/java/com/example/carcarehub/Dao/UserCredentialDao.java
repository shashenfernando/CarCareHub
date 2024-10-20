package com.example.carcarehub.Dao;

import com.example.carcarehub.domain.User;
import com.example.carcarehub.domain.UserCredential;

public interface UserCredentialDao {
    public UserCredential createUserCredential(UserCredential userCredential);

    void deleteUserCredentials(User user);
}
