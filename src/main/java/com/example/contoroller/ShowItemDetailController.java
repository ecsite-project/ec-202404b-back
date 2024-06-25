
package com.example.contoroller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.ShowItemDetailService;

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

    /**
     * Getでもらった商品idを検索し、その商品を返す.
     *　
     * @param id 商品id
     * @return 商品の詳細
     */
    @GetMapping("")
    public ResponseEntity<?> showDetailPage(@RequestParam("id") String id) {
        try {
            return ResponseEntity.ok(service.getDetail(UUID.fromString(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
