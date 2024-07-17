package com.sami.booking_system.controller;


import com.sami.booking_system.dto.MailBody;
import com.sami.booking_system.entity.ForgotPassword;
import com.sami.booking_system.entity.User;
import com.sami.booking_system.exceptions.CustomMessageException;
import com.sami.booking_system.repository.ForgotPasswordRepository;
import com.sami.booking_system.repository.UserRepository;
import com.sami.booking_system.service.EmailService;
import com.sami.booking_system.utils.ChangePassword;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

import static com.sami.booking_system.exceptions.ApiError.fieldError;
import static com.sami.booking_system.utils.ResponseBuilder.error;
import static com.sami.booking_system.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@Tag(name = "Forgot Password API")
@RequestMapping("/api/v1/forgotPassword")
public class ForgotPasswordController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailService emailService;
    @Autowired
    ForgotPasswordRepository forgotPasswordRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    // send mail for email verification
    @PostMapping("/verifyMail/{email}")
    public ResponseEntity<JSONObject> verifyEmail(@PathVariable String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Please provide an valid email!" + email));

        int otp = otpGenerator();
        MailBody mailBody = MailBody.builder()
                .to(email)
                .text("This is the OTP for your Forgot Password request : " + otp)
                .subject("OTP for Forgot Password request")
                .build();

        ForgotPassword fp = ForgotPassword.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis() + 60 * 1000))
                .user(user)
                .build();

        emailService.sendSimpleMessage(mailBody);
        forgotPasswordRepository.save(fp);

//        return ResponseEntity.ok("Email sent for verification!");

        return ok(success(null, "Email sent for verification!").getJson());
    }


    @PostMapping("/verifyOtp/{otp}/{email}")
    public ResponseEntity<JSONObject> verifyOtp(@PathVariable Integer otp, @PathVariable String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomMessageException("Please provide an valid email!"));

        ForgotPassword fp = forgotPasswordRepository.findByOtpAndUser(otp, user)
                .orElseThrow(() -> new CustomMessageException("Invalid OTP for email: " + email));

        if (fp.getExpirationTime().before(Date.from(Instant.now()))) {
            forgotPasswordRepository.deleteById(fp.getFpid());
            return badRequest().body(error(null, "OTP has expired!").getJson());
        }

//        return ResponseEntity.ok("OTP verified!");

        return ok(success(null, "OTP verified!").getJson());
    }

    @PostMapping("/changePassword/{email}")
    public ResponseEntity<JSONObject> changePasswordHandler(@RequestBody ChangePassword changePassword,
                                                        @PathVariable String email) {
        if (!Objects.equals(changePassword.password(), changePassword.repeatPassword())) {
//            return new ResponseEntity<>("Please enter the password again!", HttpStatus.EXPECTATION_FAILED);
          return   badRequest().body(error(null, "Password don't match, please enter the password again!").getJson());
        }

        String encodedPassword = passwordEncoder.encode(changePassword.password());
        userRepository.updatePassword(email, encodedPassword);

//        return ResponseEntity.ok("Password has been changed!");

        return ok(success(null, "Password has been changed!").getJson());
    }


    private Integer otpGenerator() {
        Random random = new Random();
        return random.nextInt(100_000, 999_999);
    }

}
