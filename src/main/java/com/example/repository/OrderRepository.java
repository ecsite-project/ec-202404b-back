package com.example.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.Order;
import com.example.domain.OrderStatus;

/**
 * カートを検索するリポジトリクラス.
 *
 * @author tugukurechan
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    /**
     * statusとuserIdで検索する.
     *
     * @param status 注文のステータス
     * @param id Userのid
     * @return statusが注文前でかつuserIdが一致するOrder
     */
    Order findByStatusAndUserId(OrderStatus status, UUID id);

    /**
     * userIdのOrderが存在するか.
     *
     * @param useId userId
     * @return true or false
     */
    Boolean existsByUserId(UUID useId);
}
