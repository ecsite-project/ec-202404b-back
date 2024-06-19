package com.example.service;

import com.example.domain.User;
import com.example.dtos.UserRegistrationDto;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author char5742
 */
@Service
public class CreateUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User create(UserRegistrationDto registrationDto) throws Exception {
        if (userRepository.findByEmail(registrationDto.getEmail()) != null) {
            throw new Exception("Email already in use");
        }

        if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
            throw new Exception("Passwords do not match");
        }

        User user = new User();
        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setZipcode(registrationDto.getZipcode());
        user.setPrefecture(registrationDto.getPrefecture());
        user.setMunicipalities(registrationDto.getMunicipalities());
        user.setAddress(registrationDto.getAddress());
        user.setTelephone(registrationDto.getTelephone());

        return userRepository.save(user);
    }
}
