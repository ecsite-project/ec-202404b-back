package com.example.repository;

import com.example.domain.Breed;
import com.example.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * 表示と検索のリポジトリクラス
 *
 * @author takeru.chugun
 */
@Repository
public interface ItemRepository extends JpaRepository<Item,UUID>{
    /** 上限価格、下限価格、種別、色で検索 */
    List<Item> findByPriceBetweenAndBreedIdAndColorIdIn(Double minPrice, Double maxPrice, UUID breed, List<UUID> colorIds);
    /** 上限価格、下限価格、色で検索 */
    List<Item> findByPriceBetweenAndColorIdIn(Double minPrice, Double maxPrice, List<UUID> colorIds);
    /** 上限価格、下限価格で検索 */
    List<Item> findByPriceBetween(Double minPrice, Double maxPrice);

    /*---------ページング用---------*/
    /** 上限価格、下限価格、種別、色で検索 */
    Page<Item> findByPriceBetweenAndBreedIdAndColorIdIn(Double minPrice, Double maxPrice, UUID breed, List<UUID> colorIds, Pageable pageable);
    /** 上限価格、下限価格、色で検索 */
    Page<Item> findByPriceBetweenAndColorIdIn(Double minPrice, Double maxPrice, List<UUID> colorIds, Pageable pageable);
    /** 上限価格、下限価格で検索 */
    Page<Item> findByPriceBetween(Double minPrice, Double maxPrice, Pageable pageable);
}
