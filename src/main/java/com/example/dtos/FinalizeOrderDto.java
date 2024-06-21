package com.example.dtos;

import com.example.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 注文確定時に送られるもののDTOクラス.
 *
 * @author takeru.chugun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinalizeOrderDto {
    // userIdでOrderを検索するため
    private String userId;

    // Orderに更新していく
    private String name;
    private String email;
    private String zipcode;
    private String prefecture;
    private String municipalities;
    private String address;
    private String telephone;
    private String deliveryDate;
    private String deliveryTime;
    private String paymentMethod;

    //注文商品リスト
    private List<String> OrderItemIdList;
}
