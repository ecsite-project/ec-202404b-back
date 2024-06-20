
package com.example.contoroller;

import com.example.domain.Option;
import com.example.dtos.AddItemDto;
import com.example.repository.OptionRepository;
import com.example.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * @author char5742
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
//            return ResponseEntity.ok(service.getShoppingCart());
//        }

        return null;
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        try {
            AddItemDto form = new AddItemDto();
            form.setItemId("97da7257-354a-45d5-96cc-b9e8a532587d");
            List<String> idList = List.of("066c818c-f70d-487f-91b3-86e5ef13732c","0c3bbe65-1b14-4e93-8ab2-a1859f694ecf");
            form.setOptionIdList(idList);
            shoppingCartService.addItem(form);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return null;
    }
}
