
package com.example.service;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderStatus;
import com.example.domain.TimeRange;
import com.example.dtos.FinalizeOrderDto;
import com.example.dtos.PaymentInfoDTO;
import com.example.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class FinalizeOrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CreditCardService creditCardService;

    /**
     * 注文商品のリストとクレカのフォーム（クレカ払いのとき）を受け取り
     * Order情報を更新する.
     *
     * @param form 注文商品の詳細
     * @param paymentInfo クレカ情報の詳細
     * @return 支払いが成功したらnull、失敗したらerror
     * @throws JsonProcessingException exception
     */
    public String finalize(FinalizeOrderDto form, PaymentInfoDTO paymentInfo) throws JsonProcessingException {
        Order order = orderRepository.findByStatusAndUserId(OrderStatus.BEFORE_ORDER, UUID.fromString(form.getUserId()));
        // 0(注文前)->1(未入金)
        order.setStatus(OrderStatus.UNPAID);

        //total price
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

        // 配達日時の設定
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            order.setDeliveryDate(LocalDate.parse(form.getDeliveryDate(), formatter));
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        order.setTimeRange(TimeRange.fromDisplayName(form.getDeliveryTime()));

        order.setPaymentMethod(form.getPaymentMethod());
        orderRepository.save(order);

        // 支払い方法がクレカの場合
        // 現金払いの場合はstatus=UNPAID(1)のまま
        if(form.getPaymentMethod().equals("クレカ")){
            paymentInfo.setOrderNumber(String.valueOf(order.getId()));
            paymentInfo.setAmount(order.getTotalPrice());
            val result = creditCardService.callApi(paymentInfo);
            if(result.getStatus().equals("success")){
                // status = PAID(2)は入金済み
                order.setStatus(OrderStatus.PAID);
                orderRepository.save(order);
            }else{
                // クレカのエラーメッセージを出力
                //
                return result.getMessage();
            }
        }
        return null;
    }
}
