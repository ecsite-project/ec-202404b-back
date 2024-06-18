package com.example.contoroller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author char5742
 */
@RestController
public class AuthenticationController {

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginForm form, HttpServletResponse response) {
        if (false) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return Map.of("message", "Invalid credentials");
        }
        return Map.of("jwt", "token");

    }

    record LoginForm(String email, String password) {

    }
}
