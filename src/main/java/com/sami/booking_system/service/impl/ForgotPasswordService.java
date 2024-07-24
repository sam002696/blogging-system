package com.sami.booking_system.service.impl;

import com.sami.booking_system.dto.MailBody;
import com.sami.booking_system.entity.ForgotPassword;
import com.sami.booking_system.entity.User;
import com.sami.booking_system.exceptions.CustomMessageException;
import com.sami.booking_system.repository.ForgotPasswordRepository;
import com.sami.booking_system.repository.UserRepository;
import com.sami.booking_system.service.EmailService;
import com.sami.booking_system.utils.ChangePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import com.sami.booking_system.service.interfaces.IForgotPasswordService;

@Service
public class ForgotPasswordService implements IForgotPasswordService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailService emailService;
    @Autowired
    ForgotPasswordRepository forgotPasswordRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public User verifyEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomMessageException("Please provide a valid email! " + email));

        int otp = otpGenerator();
        MailBody mailBody = buildMailBody(email, otp);

        // Check if an entry for the user already exists
        Optional<ForgotPassword> existingFp = forgotPasswordRepository.findByUser(user);

        ForgotPassword fp;
        if (existingFp.isPresent()) {
            // Update the existing entry
            fp = existingFp.get();
            fp.setOtp(otp);
            fp.setExpirationTime(new Date(System.currentTimeMillis() + 60 * 1000));
        } else {
            // Create a new entry
            fp = ForgotPassword.builder()
                    .otp(otp)
                    .expirationTime(new Date(System.currentTimeMillis() + 60 * 1000))
                    .user(user)
                    .build();
        }

        emailService.sendSimpleMessage(mailBody);
        forgotPasswordRepository.save(fp);

        return user;
    }


    @Override
    public ForgotPassword verifyOtp(Integer otp, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomMessageException("Please provide a valid email!"));

        ForgotPassword fp = forgotPasswordRepository.findByOtpAndUser(otp, user)
                .orElseThrow(() -> new CustomMessageException("Invalid OTP for email: " + email));

        if (fp.getExpirationTime().before(Date.from(Instant.now()))) {
            forgotPasswordRepository.deleteById(fp.getFpid());
            throw new CustomMessageException("OTP has expired!");
        }

        return fp;
    }

    @Override
    public void changePassword(ChangePassword changePassword, String email) {
        if (!Objects.equals(changePassword.password(), changePassword.repeatPassword())) {
            throw new CustomMessageException("Passwords don't match, please enter the password again!");
        }

        String encodedPassword = passwordEncoder.encode(changePassword.password());
        userRepository.updatePassword(email, encodedPassword);
    }

    @Override
    public MailBody buildMailBody(String email, int otp) {
        return MailBody.builder()
                .to(email)
                .text("This is the OTP for your Forgot Password request: " + otp)
                .subject("OTP for Forgot Password request")
                .build();
    }

    @Override
    public Integer otpGenerator() {
        Random random = new Random();
        return random.nextInt(100_000, 999_999);
    }

}
