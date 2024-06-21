package com.example.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Order;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    Order findByStatusAndUserId(Integer status, UUID id);

    Boolean existsByUserId(UUID useId);
}
