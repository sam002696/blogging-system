package com.sami.booking_system.controller;

import com.sami.booking_system.dto.*;
import com.sami.booking_system.entity.Post;
import com.sami.booking_system.entity.User;
import com.sami.booking_system.responses.LoginResponse;
import com.sami.booking_system.service.interfaces.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.sami.booking_system.exceptions.ApiError.fieldError;
import static com.sami.booking_system.utils.ResponseBuilder.error;
import static com.sami.booking_system.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@Tag(name = "Auth API")
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    @Operation(summary = "Register a user", responses = {
            @ApiResponse(description = "Successfully registered",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RegisterRequest.class)))
    })
    public ResponseEntity<JSONObject> register(@RequestBody @Valid RegisterRequest registerRequest, BindingResult result) {
        if (result.hasErrors()) {
            return badRequest().body(error(fieldError(result)).getJson());
        }

        User user = userService.register(registerRequest);
        return ok(success(user, "User added successfully").getJson());
    }



    @PostMapping("/login")
    @Operation(summary = "Login a user", responses = {
            @ApiResponse(description = "Successfully logged in",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginRequest.class)))
    })
    public ResponseEntity<JSONObject> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = userService.login(loginRequest);
        return ok(success(response, "Logged in successfully").getJson());
    }
}
