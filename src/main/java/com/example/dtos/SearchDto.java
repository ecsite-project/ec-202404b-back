package com.example.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {
    /** 最大料金 */
    private String maxPrice;
    /** 最小料金 */
    private String minPrice;
    /** 色 */
    private List<UUID> colorList;
    /** 種 */
    private String breedId;
}
