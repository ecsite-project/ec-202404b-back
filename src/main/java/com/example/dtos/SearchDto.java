package com.example.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品を検索するときのDTOクラス.
 *
 * @author tugukurechan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {
    /** 最大料金 */
    private String maxPrice;
    /** 最小料金 */
    private String minPrice;
    /** 色 */
    private List<String> colorList;
    /** 種 */
    private String breed = "";
}
