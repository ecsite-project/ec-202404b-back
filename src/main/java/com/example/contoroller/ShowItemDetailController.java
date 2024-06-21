
package com.example.contoroller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.ShowItemDetailService;

import lombok.val;

/**
 * 詳細ページを操作するコントローラクラス.
 *
 * @author takeru.chugun
 */
@RestController
@RequestMapping("/showDetail")
public class ShowItemDetailController {
    @Autowired
    private ShowItemDetailService service;

    @GetMapping("")
    public ResponseEntity<?> showDetailPage(String id) {
        try {
            // UUID uuid = UUID.fromString(id);

            val uuid = UUID.fromString("97da7257-354a-45d5-96cc-b9e8a532587d");
            return ResponseEntity.ok(service.getDetail(uuid));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
