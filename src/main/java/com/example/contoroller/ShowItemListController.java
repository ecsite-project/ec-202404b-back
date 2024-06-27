
package com.example.contoroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Item;
import com.example.dtos.PageRequestDto;
import com.example.dtos.PageResponseDto;
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
            return ResponseEntity.ok(service.search(form));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    record PagingRequest(SearchDto search, PageRequestDto page) {
    }

    /**
     * 商品一覧をページ付きで表示.
     *
     * @param pagingRequest ページ機能のリクエスト
     * @return リスト化された商品一覧
     */
    @PostMapping("/page")
    public ResponseEntity<?> getPage(@RequestBody PagingRequest pagingRequest) {
        try {
            SearchDto condition = pagingRequest.search();
            int page = pagingRequest.page().getCurrentPage() - 1; // 1-based index to 0-based
            int size = pagingRequest.page().getPerPage();
            Pageable pageable = PageRequest.of(page, size);
            Page<Item> items = service.search(condition, pageable);

            int currentPage = items.getNumber() + 1; // 0-based index to 1-based
            int perPage = items.getSize();
            int lastPage = items.getTotalPages();
            int total = (int) items.getTotalElements();

            PageResponseDto.Metadata metadata = new PageResponseDto.Metadata(currentPage, perPage, lastPage, total);
            PageResponseDto<Item> response = new PageResponseDto<>(metadata, items.stream().toList());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 全色の情報を返す.
     *
     * @return 全色の情報
     */
    @GetMapping("/getColor")
    public ResponseEntity<?> getColor(){
        try{
            return ResponseEntity.ok(service.findAllColor());
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 全品種の情報を返す.
     *
     * @return 全品種の情報
     */
    @GetMapping("/getBreed")
    public ResponseEntity<?> getBreed(){
        try{
            return ResponseEntity.ok(service.findAllBreed());
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
