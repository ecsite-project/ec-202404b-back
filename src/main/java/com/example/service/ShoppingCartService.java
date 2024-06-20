
package com.example.service;

import com.example.domain.Item;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.dtos.AddItemDto;
import com.example.repository.ItemRepository;
import com.example.repository.OptionRepository;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

/**
 * ショッピングカートを操作するサービスクラス.
 *
 * @author takeru.chugun
 */
public class ShoppingCartService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OptionRepository optionRepository;
    /**
     * ショッピングカートを検索させる.
     *
     * @param userId ユーザid
     * @return ユーザのショッピングカート
     */
    public Order getShoppingCart(UUID userId){
        var optionalOrder = orderRepository.findById(userId);
        return optionalOrder.orElse(null);
    }

    public void addItem(AddItemDto form){
//        @AuthenticationPrincipal AuthenticationPrincipal();
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        authentication.getPrincipal();
        // 追加するアイテム
        var item = itemRepository.findById(UUID.fromString(form.getItemId())).get();
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        var optionIdList = form.getOptionIdList().stream().map(UUID::fromString).toList();

        Order order = new Order();
        order.getOrderItemList().add(orderItem);

        orderRepository.save(order);
    }

}
