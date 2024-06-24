
package com.example.contoroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.SearchDto;
import com.example.service.ShowItemListService;

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

    /**
     * 商品一覧を返すメソッド.
     *
     * @return 全商品を返すメソッド
     */
    @GetMapping("")
    public ResponseEntity<?> getAllItem() {
        try {
            return ResponseEntity.ok(service.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 商品検索（検索：上限価格、下限価格、色、種別）をする.
     *
     * @return 検索結果のリスト
     */
    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody SearchDto form) {
        try {
//            バックエンドのテスト用
//            val form = new SearchDto();
//            val colorList = new ArrayList<>(List.of(
//                    UUID.fromString("1177eb09-8443-4670-b903-362d3cd135f0"),
//                    UUID.fromString("146b2622-5838-49a0-8db2-599676e8b673")));
//            form.setColorList(colorList);
//            form.setMaxPrice("100000");
//            form.setMinPrice("60000");
//            form.setBreedId("3854607f-019f-4591-9ab1-95ac496ba728");
            return ResponseEntity.ok(service.search(form));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
