package com.sami.booking_system.service.impl;

import com.sami.booking_system.dto.LoginRequest;
import com.sami.booking_system.dto.RegisterRequest;
import com.sami.booking_system.dto.Response;
import com.sami.booking_system.dto.UserDTO;
import com.sami.booking_system.entity.User;
import com.sami.booking_system.exception.OurException;
import com.sami.booking_system.exceptions.CustomMessageException;
import com.sami.booking_system.repository.UserRepository;
import com.sami.booking_system.service.interfaces.IUserService;
//import com.sami.booking_system.utils.JwtService;
import com.sami.booking_system.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private JwtService jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;


//    @Override
//    public Response register(RegisterRequest user) {
//        Response response = new Response();
//        try {
//            if (user.getRole() == null || user.getRole().isBlank()) {
//                user.setRole("USER");
//            }
//            if (userRepository.existsByEmail(user.getEmail())) {
//                throw new OurException(user.getEmail() + "Already Exists");
//            }
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            User savedUser = userRepository.save(user);
//            UserDTO userDTO = Utils.mapUserEntityToUserDTO(savedUser, true);
//            response.setStatusCode(200);
//            response.setUser(userDTO);
//        } catch (OurException e) {
//            response.setStatusCode(400);
//            response.setMessage(e.getMessage());
//        } catch (Exception e) {
//            response.setStatusCode(500);
//            response.setMessage("Error Occurred During USer Registration " + e.getMessage());
//
//        }
//        return response;
//    }

    @Override
    public User register(RegisterRequest reqUser) {
        User user = new User();
        if (reqUser.getRole() == null || reqUser.getRole().isEmpty()) {
            reqUser.setRole("USER");
        }
        if (userRepository.existsByEmail(reqUser.getEmail())) {
                throw new CustomMessageException(reqUser.getEmail() + "Already Exists");
            }
        reqUser.setPassword(passwordEncoder.encode(reqUser.getPassword()));

        user.setEmail(reqUser.getEmail());
        user.setName(reqUser.getName());
        user.setRole(reqUser.getRole());
        user.setPassword(reqUser.getPassword());
        user.setPhoneNumber(reqUser.getPhoneNumber());

        return userRepository.save(user);

    }

    @Override
    public Response login(LoginRequest loginRequest) {
        Response response = new Response();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new OurException("user Not found"));

//            var token = jwtUtils.generateToken(user);
            response.setStatusCode(200);
//            response.setToken(token);
            response.setRole(user.getRole());
            response.setExpirationTime("7 Days");
            response.setMessage("successful");

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {

            response.setStatusCode(500);
            response.setMessage("Error Occurred During USer Login " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAllUsers() {
        Response response = new Response();
        try {
            List<User> userList = userRepository.findAll();
            List<UserDTO> userDTOList = Utils.mapUserListEntityToUserListDTO(userList);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUserList(userDTOList);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error getting all users " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response deleteUser(String userId) {
        Response response = new Response();

        try {
            userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new OurException("User Not Found"));
            userRepository.deleteById(Long.valueOf(userId));
            response.setStatusCode(200);
            response.setMessage("successful");

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {

            response.setStatusCode(500);
            response.setMessage("Error getting all users " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getMyInfo(String email) {
        Response response = new Response();

        try {
            User user = userRepository.findByEmail(email).orElseThrow(() -> new OurException("User Not Found"));
            UserDTO userDTO = Utils.mapUserEntityToUserDTO(user, true);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUser(userDTO);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {

            response.setStatusCode(500);
            response.setMessage("Error getting the user " + e.getMessage());
        }
        return response;
    }
}
