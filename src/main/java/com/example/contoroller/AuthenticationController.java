package com.example.contoroller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.LoginDto;
import com.example.service.AuthenticationService;
import com.example.service.ShoppingCartService;

import lombok.val;

/**
 *
 * @author char5742
 */
@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {

        val response = authenticationService.login(loginDto);
        val token = response.token();
        val user = response.user();
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        if (loginDto.getAnonymous() != null) {

            shoppingCartService.migration(
                    UUID.fromString(loginDto.getAnonymous()), user.getId());
        }
        return ResponseEntity.ok(token);

    }
}
