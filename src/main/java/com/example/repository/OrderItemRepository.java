package com.example.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
}
