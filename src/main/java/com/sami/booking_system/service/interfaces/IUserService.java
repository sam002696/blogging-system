package com.sami.booking_system.service.interfaces;

import com.sami.booking_system.dto.LoginRequest;
import com.sami.booking_system.dto.RegisterRequest;
import com.sami.booking_system.dto.Response;
import com.sami.booking_system.dto.UserDTO;
import com.sami.booking_system.entity.User;
import com.sami.booking_system.responses.LoginResponse;

import java.util.Map;

public interface IUserService {
    User register(RegisterRequest user);

    LoginResponse login(LoginRequest loginRequest);

    Response getAllUsers();

    Response deleteUser(String userId);

//    Response getMyInfo(String email);
UserDTO getMyInfo(String email);

    Map<String, Object> search(Integer page, Integer size, String sortBy, String search);
}
