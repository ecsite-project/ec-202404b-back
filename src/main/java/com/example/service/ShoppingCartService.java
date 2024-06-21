
package com.example.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderStatus;
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
 * @author takeru.chugun
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
        var item = itemRepository.findById(UUID.fromString(form.getItemId())).orElse(null);
        var options = form.getOptionIdList().stream()
                .map(optionId -> optionRepository.findById(UUID.fromString(optionId))
                        .orElseThrow(() -> new RuntimeException("Option not found: " + optionId)))
                .collect(Collectors.toList());

        var orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOptions(options);
        orderItem = orderItemRepository.save(orderItem);

        var order = new Order();
        order.setStatus(OrderStatus.BEFORE_ORDER);
        order.setTotalPrice(10);
        order.setOrderDate(LocalDate.now());
        order.setOrderItems(List.of(orderItem));

        orderItem.setOrder(orderRepository.save(order));
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
