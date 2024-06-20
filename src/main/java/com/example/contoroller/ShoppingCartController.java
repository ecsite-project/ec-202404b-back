
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
import java.util.concurrent.ExecutionException;

/**
 * @author char5742
 */
@RestController
@RequestMapping("/api/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService service;

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
            AddItemDto addItemDto = new AddItemDto();
            addItemDto.setItemId("97da7257-354a-45d5-96cc-b9e8a532587d");
            addItemDto.setOptionIdList(optionRepository.findAll().stream().map(String::valueOf).toList());
            service.addItem(addItemDto);
            return ResponseEntity.ok("");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
