package com.example.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.OrderStatus;
import com.example.dtos.UserInfo;
import com.example.repositories.UserRepository;
import com.example.repository.OrderRepository;

import lombok.val;

@Service
public class UserInfoService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    public UserInfo getUserInfo(UUID id) {
        val user = userRepository.findById(id).get();
        val orderList = orderRepository.findAllByStatusAndUserId(OrderStatus.SHIPPED, id);
        val info = UserInfo.builder()
                .name(user.getLastName() + " " + user.getFirstName())
                .email(user.getEmail())
                .zipcode(user.getZipcode())
                .prefecture(user.getPrefecture())
                .municipalities(user.getMunicipalities())
                .address(user.getAddress())
                .telephone(user.getTelephone())
                .orderHistory(
                        orderList)
                .build();
        return info;
    }

}
