package com.example.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;

/**
 * 表示と検索のリポジトリクラス
 *
 * @author takeru.chugun
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {
    /** 上限価格、下限価格、種別、色で検索 */
    List<Item> findByPriceBetweenAndBreedIdAndColorIdIn(Double minPrice, Double maxPrice, UUID breed,
            List<UUID> colorIds);

    /** 上限価格、下限価格、色で検索 */
    List<Item> findByPriceBetweenAndColorIdIn(Double minPrice, Double maxPrice, List<UUID> colorIds);

    /** 上限価格、下限価格で検索 */
    List<Item> findByPriceBetween(Double minPrice, Double maxPrice);

    Item findByDescription(String description);
}
