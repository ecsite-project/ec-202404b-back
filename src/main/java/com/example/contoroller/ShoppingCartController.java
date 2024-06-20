
package com.example.contoroller;

import com.example.domain.Option;
import com.example.dtos.AddItemDto;
import com.example.repository.OptionRepository;
import com.example.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

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
    OptionRepository optionRepository;

    @GetMapping("/getShoppingCart")
    public ResponseEntity<?> showShoppingCart() {
//        try{
//            @AuthenticationPrincipal
//
//            return ResponseEntity.ok(service.getShoppingCart());
//        }catch(Exception e){
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }

        return null;
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
