package com.example.contoroller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.AddItemDto;
import com.example.dtos.DeleteItemDto;
import com.example.dtos.GetShoppingCartDto;
import com.example.repository.OptionRepository;
import com.example.service.ShoppingCartService;

/**
 * カートを操作するコントローラクラス.
 *
 * @author takeru.chugun
 * @author mun
 */
@RestController
@RequestMapping("/api/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private OptionRepository optionRepository;

    /**
     * ショッピングカートをid検索して表示する.
     *
     * @param form ユーザidが入ったフォーム
     * @return ユーザのカート情報
     */
    @PostMapping("/getShoppingCart")
    public ResponseEntity<?> showShoppingCart(@RequestBody GetShoppingCartDto form) {
        try {
            if (shoppingCartService.isUserId(UUID.fromString(form.getUserId()))) {
                // すでにOrderがあったら
                return ResponseEntity.ok(shoppingCartService.getShoppingCart(form));
            }
            // なかったら
            shoppingCartService.createOrder(UUID.fromString(form.getUserId()));
            return ResponseEntity.ok(shoppingCartService.getShoppingCart(form));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * カートにitemを追加する.
     *
     * @param form 追加するitemIdと選択されたoptionのListのフォーム
     * @return 追加に成功かエラーのメッセージ
     */
    @PostMapping("/addItem")
    public ResponseEntity<?> test(@RequestBody AddItemDto form) {
        try {
            shoppingCartService.addItem(form);
            return ResponseEntity.ok("success adding item");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * カートに入った商品を削除する.
     *
     * @param form 削除する商品(OrderItem)のid
     * @return 成功かエラーメッセージ
     */
    @PostMapping("/deleteItem")
    public ResponseEntity<?> delete(@RequestBody DeleteItemDto form) {
        try {
            shoppingCartService.deleteByOrderItemId(UUID.fromString(form.getOrderItemId()));
            return ResponseEntity.ok("success deleting orderItem");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
