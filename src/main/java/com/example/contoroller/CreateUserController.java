package com.example.contoroller;

import com.example.dtos.UserRegistrationDto;
import com.example.service.CreateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author mun018
 * @author char5742
 */
@RestController
@RequestMapping("/api/register")
public class CreateUserController {

    @Autowired
    private CreateUserService createUserService;

    @GetMapping
    public ResponseEntity<String> showCreatePage(){
        return ResponseEntity.ok("Process at the front server");
    }

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDto registrationDto) {
        try {
            createUserService.create(registrationDto);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
