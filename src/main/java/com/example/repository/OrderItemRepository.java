package com.example.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.OrderItem;
import org.springframework.stereotype.Repository;

/**
 * 注文商品を操作するリポジトリクラス.
 *
 * @author tugukurechan
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
}
