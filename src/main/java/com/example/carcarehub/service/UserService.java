package com.example.carcarehub.service;

import com.example.carcarehub.model.request.UpdateUser;
import com.example.carcarehub.model.request.UserRegistrationRequest;
import com.example.carcarehub.model.response.*;

import java.util.HashMap;
import java.util.List;

public interface UserService {
   public UserRegistrationResponse createUser(UserRegistrationRequest userRegistrationRequest) throws Exception;
    public UserResponse getUserById(int userId) throws Exception;
   public List<CarCareHubUserListResponse> getCarCareHubUsersList() throws Exception;
    public UpdateUserResponse updateUser(int userId, UpdateUser updateUser) throws Exception;
    public HashMap<String, String> deleteCarCareHubUserById(int userid) throws Exception;
    public ServiceStationResponse findStation(int stationId) throws Exception;
}
