package com.example.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品を追加するDTO.
 *
 * @author takeru.chugun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddItemDto {
    /**ユーザID*/
    private String UserId;
    /** 商品id(String) */
    private String itemId;
    /** 選択されたオプションのUUID(String) */
    private List<String> optionIdList;
}
