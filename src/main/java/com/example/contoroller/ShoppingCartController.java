
package com.example.contoroller;

import com.example.domain.Option;
import com.example.dtos.AddItemDto;
import com.example.repository.OptionRepository;
import com.example.service.ShoppingCartService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping("/getShoppingCart")
    public ResponseEntity<?> showShoppingCart() {
        try{
            return ResponseEntity.ok();
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

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
