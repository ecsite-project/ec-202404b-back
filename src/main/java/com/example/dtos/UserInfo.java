package com.example.dtos;

import java.util.List;

import com.example.domain.Order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {

    private String name;
    private String email;
    private String zipcode;
    private String prefecture;
    private String municipalities;
    private String address;
    private String telephone;

    private List<Order> orderHistory;

}
