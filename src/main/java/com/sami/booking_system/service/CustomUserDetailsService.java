package com.sami.booking_system.service;

import com.sami.booking_system.entity.User;
import com.sami.booking_system.exception.OurException;
import com.sami.booking_system.exceptions.CustomMessageException;
import com.sami.booking_system.repository.UserRepository;
import com.sami.booking_system.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new CustomMessageException("Username/Email not Found"));
        return UserPrincipal.create(user);
    }
}
