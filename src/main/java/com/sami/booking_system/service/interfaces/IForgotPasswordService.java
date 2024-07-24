package com.sami.booking_system.service.interfaces;



import com.sami.booking_system.dto.MailBody;
import com.sami.booking_system.entity.ForgotPassword;
import com.sami.booking_system.entity.User;
import com.sami.booking_system.utils.ChangePassword;

public interface IForgotPasswordService {
    User verifyEmail(String email);

    ForgotPassword verifyOtp(Integer otp, String email);

    void changePassword(ChangePassword changePassword, String email);

    MailBody buildMailBody(String email, int otp);

    Integer otpGenerator();
}
