package com.example.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.OrderItem;

/**
 * 注文商品を操作するリポジトリクラス.
 *
 * @author tugukurechan
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
}
