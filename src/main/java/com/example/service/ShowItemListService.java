
package com.example.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.domain.Item;
import com.example.dtos.SearchDto;
import com.example.repository.BreedRepository;
import com.example.repository.ColorRepository;
import com.example.repository.ItemRepository;

import lombok.val;

/**
 * 商品を表示したり、検索をするサービスクラス.
 *
 * @author takeru.chugun
 */
@Service
public class ShowItemListService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BreedRepository breedRepository;

    @Autowired
    private ColorRepository colorRepository;

    /**
     * 全商品を取得する.
     *
     * @return 全商品
     */
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    /**
     * ＜検索条件＞上限価格、下限価格、種別、色
     *
     * @param form フォーム
     * @return 検索結果
     */
    public List<Item> search(SearchDto form) {
        List<UUID> colorIdList = form.getColorList().stream().map(colorRepository::findByName).map(e -> e.getId())
                .toList();
        if (form.getBreed() == null && colorIdList.isEmpty()) {
            return itemRepository.findByPriceBetween(Double.parseDouble(form.getMinPrice()),
                    Double.parseDouble(form.getMaxPrice()));
        } else if (form.getBreed() == null) {
            return itemRepository.findByPriceBetweenAndColorIdIn(Double.parseDouble(form.getMinPrice()),
                    Double.parseDouble(form.getMaxPrice()), colorIdList);
        } else {
            val breed = breedRepository.findByName(form.getBreed());
            return itemRepository.findByPriceBetweenAndBreedIdAndColorIdIn(
                    Double.parseDouble(form.getMinPrice()),
                    Double.parseDouble(form.getMaxPrice()),
                    breed.getId(), colorIdList);
        }
    }

    /**
     * 検索をしてページで表示する.
     *
     * @param condition 検索フォーム
     * @param pageable ページ
     * @return 検索後の商品のリスト
     */
    public Page<Item> search(SearchDto condition, Pageable pageable) {
        List<UUID> colorIdList = condition.getColorList().stream().map(colorRepository::findByName).map(e -> e.getId())
                .toList();
        val hasNotBreed = condition.getBreed().isEmpty();

        /*
         * 条件: Min < 値段 < Max
         */
        if (hasNotBreed && colorIdList.isEmpty()) {
            return itemRepository.findByPriceBetweenOrderByPriceAsc(
                    Double.parseDouble(condition.getMinPrice()),
                    Double.parseDouble(condition.getMaxPrice()),
                    pageable);
        }

        /*
         * 条件: Min < 値段 < Max and 色リスト
         */
        if (hasNotBreed) {
            return itemRepository.findByPriceBetweenAndColorIdInOrderByPriceAsc(
                    Double.parseDouble(condition.getMinPrice()),
                    Double.parseDouble(condition.getMaxPrice()),
                    colorIdList,
                    pageable);
        }

        if (colorIdList.isEmpty()) {
            return itemRepository.findByPriceBetweenAndBreedIdOrderByPriceAsc(
                    Double.parseDouble(condition.getMinPrice()),
                    Double.parseDouble(condition.getMaxPrice()),
                    breedRepository.findByName(condition.getBreed()).getId(),
                    pageable);
        }

        /*
         * 条件: Min < 値段 < Max and 種別 and 色リスト
         */
        val breed = breedRepository.findByName(condition.getBreed());
        return itemRepository.findByPriceBetweenAndBreedIdAndColorIdInOrderByPriceAsc(
                Double.parseDouble(condition.getMinPrice()),
                Double.parseDouble(condition.getMaxPrice()),
                breed.getId(),
                colorIdList,
                pageable);
    }
}
