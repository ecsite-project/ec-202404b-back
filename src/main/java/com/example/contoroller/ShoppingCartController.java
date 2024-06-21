
package com.example.contoroller;

import com.example.domain.Option;
import com.example.dtos.AddItemDto;
import com.example.dtos.GetShoppingCartDto;
import com.example.repository.OptionRepository;
import com.example.service.ShoppingCartService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.Normalizer;
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
     * @return OKかエラー
     */
    @PostMapping("/addItem")
    public ResponseEntity<?> test(@RequestBody AddItemDto form) {
        try {
            shoppingCartService.addItem(form);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
