
package com.example.service;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.TimeRange;
import com.example.dtos.FinalizeOrderDto;
import com.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;

/**
 * 注文確認画面を操作するサービスクラス.
 *
 * @author takeru.chugun
 */
public class FinalizeOrderService {
    @Autowired
    private OrderRepository orderRepository;

    /**
     * 購入時の届け先や支払方法などを設定する.
     *
     * @param form 注文確認画面で入力した購入情報
     */
    public void finalize(FinalizeOrderDto form) {
        Order order = orderRepository.findByStatusAndUserId(0, UUID.fromString(form.getUserId()));
        // 0(未入金)->1(入金後)
        order.setStatus(1);

        //total price
        // <OrderItem>
        var orderItemList = order.getOrderItems();
        var totalPrice = 0;
        for (var orderItem : orderItemList) {
            // オプションの合計
            for (var option : orderItem.getOptions()) {
                totalPrice += option.getPrice();
            }
            // 商品の合計
            totalPrice += orderItem.getItem().getPrice();
        }
        order.setTotalPrice(totalPrice);

        //
        order.setOrderDate(LocalDate.now());
        order.setDestinationName(form.getName());
        order.setDestinationEmail(form.getEmail());
        order.setDestinationZipcode(form.getZipcode());
        order.setDestinationPrefecture(form.getPrefecture());
        order.setDestinationMunicipalities(form.getMunicipalities());
        order.setDestinationAddress(form.getAddress());
        order.setDestinationTel(form.getTelephone());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // それ以外の
        try {
            order.setDeliveryDate(LocalDate.parse(form.getDeliveryDate(), formatter));
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
        order.setDeliveryTime(TimeRange.RANGE_8_10);
        order.setPaymentMethod(form.getPaymentMethod());
        order.setUpdatedAt(LocalDateTime.now());
        // 更新する
        orderRepository.save(order);
    }
}
