package com.example.repository;

import com.example.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    Order findByStatusAndUserId(Integer status, UUID id);
    Boolean existsByUserId(UUID useId);
}
