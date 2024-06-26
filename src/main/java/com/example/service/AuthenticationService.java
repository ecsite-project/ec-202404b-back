package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.dtos.LoginDto;
import com.example.repositories.UserRepository;
import com.example.security.JsonWebTokenUtil;

import lombok.val;

/**
 *
 * @author char5742
 */
@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JsonWebTokenUtil jwtUtil;

    public UserWithToken login(LoginDto loginDto) {
        val user = userRepository.findByEmail(loginDto.getEmail());
        if (user == null) {
            return null;
        }
        if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            val token = jwtUtil.generateToken(user.getId().toString(), user.getLastName() + " " + user.getFirstName());
            return new UserWithToken(
                    user, token);
        }
        return null;
    }

    public record UserWithToken(User user, String token) {
    };
}
