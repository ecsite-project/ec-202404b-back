
package com.example.service;

import com.example.domain.Item;
import com.example.domain.Option;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.dtos.AddItemDto;
import com.example.repository.ItemRepository;
import com.example.repository.OptionRepository;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * ショッピングカートを操作するサービスクラス.
 *
 * @author takeru.chugun
 * @author mun
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
     * @param userId ユーザid
     * @return ユーザのショッピングカート
     */
    public Order getShoppingCart(UUID userId){
        var optionalOrder = orderRepository.findById(userId);
        return optionalOrder.orElse(null);
    }

    public void addItem(AddItemDto form){
        Item item = itemRepository.findById(UUID.fromString(form.getItemId())).orElse(null);
        List<Option> options = form.getOptionIdList().stream()
                .map(optionId -> optionRepository.findById(UUID.fromString(optionId))
                        .orElseThrow(() -> new RuntimeException("Option not found: " + optionId)))
                .collect(Collectors.toList());

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOptions(options);
        orderItem = orderItemRepository.save(orderItem);

        Order order = new Order();
        order.setStatus(0);
        order.setTotalPrice(10);
        order.setOrderDate(LocalDate.now());
        order.setDestinationName("");
        order.setDestinationEmail("");
        order.setDestinationZipcode("");
        order.setDestinationPrefecture("");
        order.setDestinationMunicipalities("");
        order.setDestinationAddress("");
        order.setDestinationTel("");
        order.setDestinationMethod("");
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        order.setOrderItems(List.of(orderItem));

        orderItem.setOrder(orderRepository.save(order));
        //Update
        orderItemRepository.save(orderItem);
    }

}
