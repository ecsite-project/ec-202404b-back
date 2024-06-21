package com.example.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Order;
import com.example.domain.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    Order findByStatusAndUserId(OrderStatus status, UUID id);

    Boolean existsByUserId(UUID useId);
}
