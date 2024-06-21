package com.example.contoroller;
import com.example.dtos.AddItemDto;
import com.example.dtos.DeleteItemDto;
import com.example.dtos.GetShoppingCartDto;
import com.example.repository.OptionRepository;
import com.example.service.ShoppingCartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;


/**
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
        try{
            if(shoppingCartService.isUserId(UUID.fromString(form.getUserId()))){
                //すでにOrderがあったら
                return ResponseEntity.ok(shoppingCartService.getShoppingCart(form));
            }
            //なかったら
            shoppingCartService.createOrder(UUID.fromString(form.getUserId()));
            return ResponseEntity.ok(shoppingCartService.getShoppingCart(form));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Itemを追加する.
     *
     * @param form addするフォーム
     * @return 成功かエラーメッセージ
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
    public ResponseEntity<?> delete(@RequestBody DeleteItemDto form){
        try{
            shoppingCartService.deleteByOrderItemId(UUID.fromString(form.getOrderItemId()));
            return ResponseEntity.ok("success deleting orderItem");
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
