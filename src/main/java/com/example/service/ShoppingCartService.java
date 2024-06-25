
package com.example.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.example.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dtos.AddItemDto;
import com.example.dtos.GetShoppingCartDto;
import com.example.repository.ItemRepository;
import com.example.repository.OptionRepository;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;

import jakarta.servlet.http.HttpSession;

/**
 * ショッピングカートを操作するサービスクラス.
 *
 * @author tugukurechan
 */
@Service
public class ShoppingCartService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    OptionRepository optionRepository;

    @Autowired
    HttpSession session;

    /**
     * ショッピングカートを検索させる.
     *
     * @param form 表示するユーザのidが入ったform
     * @return ユーザのショッピングカート
     */
    public Order getShoppingCart(GetShoppingCartDto form) {
        return orderRepository.findByStatusAndUserId(OrderStatus.BEFORE_ORDER, UUID.fromString(form.getUserId()));
    }

    /**
     * 商品を追加する.
     *
     * @param form 追加する商品のidと選択されたオプションのリストが入ったフォーム
     */
    public void addItem(AddItemDto form) {
        UUID userId = UUID.fromString(form.getUserId());
        Order order = orderRepository.findByStatusAndUserId(OrderStatus.BEFORE_ORDER, userId);

        //カート存在しない時
        if (order == null){
            order = orderRepository.save(Order.builder()
                            .id(UUID.randomUUID())
                            .userId(userId)
                            .status(OrderStatus.BEFORE_ORDER)
                            .build());
        }

        var item = itemRepository.findById(UUID.fromString(form.getItemId())).orElse(null);
        if (item == null) return;

        //重複追加：削除する　⇒　新たなデータを再追加
        for(OrderItem orderItem : order.getOrderItems()){
                if (orderItem.getItem().getId().equals(item.getId())){
                    order.getOrderItems().remove(orderItem);
                    break;
                }
        }

        List<Option> options = new ArrayList<>();
        if(!form.getOptionIdList().isEmpty()) {
            options = form.getOptionIdList().stream()
                    .map(optionId -> optionRepository.findById(UUID.fromString(optionId))
                            .orElseThrow(() -> new RuntimeException("Option not found: " + optionId)))
                    .collect(Collectors.toList());
        }

        var orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setItem(item);
        orderItem.setOptions(options);
        orderItem = orderItemRepository.save(orderItem);

        //Order
        order.setStatus(OrderStatus.BEFORE_ORDER);
        order.setOrderDate(LocalDate.now());
        order.getOrderItems().add(orderItem);
        order.setOrderItems(order.getOrderItems());

        // Update
        orderItemRepository.save(orderItem);
    }

    /**
     * 任意のuserIdをもつOrderがあるかないか
     *
     * @param userId user_id
     * @return true or false
     */
    public Boolean isUserId(UUID userId) {
        return orderRepository.existsByUserId(userId);
    }

    /**
     * 新規にOrderを作成する.
     *
     * @param userId userId
     */
    public void createOrder(UUID userId) {
        var order = new Order();
        // user_idを設定する
        order.setUserId(userId);
        // デフォルト値（not nullなので）
        order.setStatus(OrderStatus.BEFORE_ORDER);
        order.setTotalPrice(0);
        order.setOrderDate(LocalDate.now());

        // 新規作成
        orderRepository.save(order);
    }

    /**
     * カート情報からそのidに該当するorderItemを削除する.
     *
     * @param orderItemId 削除するorderItemId
     */
    public void deleteByOrderItemId(UUID orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }
}
