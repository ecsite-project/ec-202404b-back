
package com.example.contoroller;

import com.example.domain.Item;
import com.example.service.ShowItemListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品一覧表示をするコントローラクラス.
 *
 * @author takeru.chugun
 */
@RestController
@RequestMapping("/api/getItemList")
public class ShowItemListController {
    @Autowired
    private ShowItemListService service;

    @GetMapping("")
    public ResponseEntity<?> getAllItem(){
        try{
            return ResponseEntity.ok(service.findAll());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
