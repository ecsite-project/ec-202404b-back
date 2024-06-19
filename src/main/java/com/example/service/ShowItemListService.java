
package com.example.service;

import com.example.domain.Item;
import com.example.dtos.SearchDto;
import com.example.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    /**
     * ＜検索条件＞上限価格、下限価格、種別、色
     *
     * @param form フォーム
     * @return 検索結果
     */
    public List<Item> search(SearchDto form) {
        if (form.getBreedId() == null && form.getColorList().isEmpty()) {
            System.out.println("お金だけで検索");
            return itemRepository.findByPriceBetween(Double.parseDouble(form.getMinPrice()),
                    Double.parseDouble(form.getMaxPrice()));
        } else if (form.getBreedId() == null) {
            System.out.println("お金と色で検索");
            return itemRepository.findByPriceBetweenAndColorIdIn(Double.parseDouble(form.getMinPrice()),
                    Double.parseDouble(form.getMaxPrice()), form.getColorList());
        } else {
            System.out.println("全条件で検索");
            return itemRepository.findByPriceBetweenAndBreedIdAndColorIdIn(
                    Double.parseDouble(form.getMinPrice()),
                    Double.parseDouble(form.getMaxPrice()),
                    UUID.fromString(form.getBreedId()), form.getColorList());
        }
    }
}
