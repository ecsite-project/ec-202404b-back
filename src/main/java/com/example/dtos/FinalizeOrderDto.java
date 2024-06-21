package com.example.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinalizeOrderDto {
    private String name;
    private String email;
    private String zipcode;
    private String prefecture;
    private String address;
    private String telephone;
    private String deliveryDate;
    private String deliveryTime;
    private String paymentMethod;
    private List<String> OrderItemIdList;
}
