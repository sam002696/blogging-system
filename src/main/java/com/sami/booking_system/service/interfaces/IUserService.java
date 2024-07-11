package com.sami.booking_system.service.interfaces;

import com.sami.booking_system.dto.LoginRequest;
import com.sami.booking_system.dto.RegisterRequest;
import com.sami.booking_system.dto.Response;
import com.sami.booking_system.entity.User;

public interface IUserService {
    User register(RegisterRequest user);

    Response login(LoginRequest loginRequest);

    Response getAllUsers();

    Response deleteUser(String userId);

    Response getMyInfo(String email);
}
