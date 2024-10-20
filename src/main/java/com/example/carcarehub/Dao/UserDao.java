package com.example.carcarehub.Dao;

import com.example.carcarehub.domain.Merchant;
import com.example.carcarehub.domain.User;

import java.util.List;

public interface UserDao {
   public User registerUser(User user);
  public User findUserByEmail(String email);
   public User findUserById(int userId);
   public List<User> getUserList();
   public User updateUser(User user);
    public void deleteUser(User user);

}
