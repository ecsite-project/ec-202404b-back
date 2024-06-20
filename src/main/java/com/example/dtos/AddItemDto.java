package com.example.dtos;

import java.util.List;
import java.util.UUID;

/**
 * 商品を追加するDTO.
 *
 * @author takeru.chugun
 */
public class AddItemDto {
    /** 商品id(String) */
    private String itemId;
    /** 選択されたオプションのUUID(String) */
    private List<String> optionIdList;
}
