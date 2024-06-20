package com.example.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * 商品を追加するDTO.
 *
 * @author takeru.chugun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddItemDto {
    /** 商品id(String) */
    private String itemId;
    /** 選択されたオプションのUUID(String) */
    private List<String> optionIdList;
}
