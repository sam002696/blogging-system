package com.sami.booking_system.controller;


import com.sami.booking_system.service.impl.ForgotPasswordService;
import com.sami.booking_system.utils.ChangePassword;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.sami.booking_system.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@Tag(name = "Forgot Password API")
@RequestMapping("/api/v1/forgotPassword")
public class ForgotPasswordController {

    @Autowired
    ForgotPasswordService forgotPasswordService;


    @PostMapping("/verifyMail/{email}")
    public ResponseEntity<JSONObject> verifyEmail(@PathVariable String email) {
        forgotPasswordService.verifyEmail(email);
        return ok(success(null, "Email sent for verification!").getJson());
    }

    @PostMapping("/verifyOtp/{otp}/{email}")
    public ResponseEntity<JSONObject> verifyOtp(@PathVariable Integer otp, @PathVariable String email) {
        forgotPasswordService.verifyOtp(otp, email);
        return ok(success(null, "OTP verified!").getJson());
    }

    @PostMapping("/changePassword/{email}")
    public ResponseEntity<JSONObject> changePasswordHandler(@RequestBody ChangePassword changePassword, @PathVariable String email) {
        forgotPasswordService.changePassword(changePassword, email);
        return ok(success(null, "Password has been changed!").getJson());
    }

}
