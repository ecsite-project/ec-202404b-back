package com.example.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mao
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {
    /**名前*/
    private String firstName;
    /**苗字*/
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private String zipcode;
    private String prefecture;
    private String municipalities;
    private String address;
    private String telephone;
}
