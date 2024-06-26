
package com.example.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Item;
import com.example.domain.Option;
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
import lombok.val;

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

        // カート存在しない時
        if (order == null) {
            order = createNewOrder(userId);
        }

        var item = itemRepository.findById(UUID.fromString(form.getItemId())).orElse(null);
        if (item == null)
            return;

        // 重複追加：削除する ⇒ 新たなデータを再追加
        for (OrderItem orderItem : order.getOrderItems()) {
            if (orderItem.getItem().getId().equals(item.getId())) {
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

        // Order
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
        return orderRepository.existsByStatusAndUserId(OrderStatus.BEFORE_ORDER, userId);
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
        order.setOrderItems(new ArrayList<OrderItem>());

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

    public void migration(UUID srcUserId, UUID destUserId) {
        val srcOrder = orderRepository.findByStatusAndUserId(OrderStatus.BEFORE_ORDER, srcUserId);
        if (srcOrder == null) {
            // もし移行元が存在しなければ何もしない
            return;
        }

        var destOrder = orderRepository.findByStatusAndUserId(OrderStatus.BEFORE_ORDER, destUserId);
        if (destOrder == null) {
            // 移行先がなければ生成
            destOrder = createNewOrder(destUserId);
        }

        val prevDestOrderItems = destOrder.getOrderItems().stream().map(OrderItem::getItem).map(Item::getId).collect(Collectors.toSet());
        for (val orderItem : srcOrder.getOrderItems()) {
            if (prevDestOrderItems.contains(orderItem.getItem().getId())) {
                // 既に存在する場合は削除し、新しいものを追加する
                destOrder.getOrderItems().remove(destOrder.getOrderItems().stream().filter(
                        e -> e.getItem().getId().equals(orderItem.getItem().getId())).findFirst().get());
            }
            orderItemRepository.save(
                    OrderItem.builder()
                        .order(destOrder)
                            .item(orderItem.getItem())
                            .options(new ArrayList<>(orderItem.getOptions()))
                            .build());
        }
        // 移行元は削除
        orderRepository.delete(srcOrder);

        orderRepository.save(destOrder);
    }

    private Order createNewOrder(UUID userId) {
        return orderRepository.save(Order.builder()
                .id(UUID.randomUUID())
                .userId(userId)
                .status(OrderStatus.BEFORE_ORDER)
                .orderItems(new ArrayList<>())
                .build());
    }
}
