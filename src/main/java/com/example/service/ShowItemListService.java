
package com.example.service;

import com.example.domain.Item;
import com.example.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品を表示したり、検索をするサービスクラス.
 *
 * @author takeru.chugun
 */
@Service
public class ShowItemListService {
    @Autowired
    private ItemRepository itemRepository;

    /**
     * 全商品を取得する.
     *
     * @return 全商品
     */
    public List<Item> findAll(){
        return itemRepository.findAll();
    }
}
