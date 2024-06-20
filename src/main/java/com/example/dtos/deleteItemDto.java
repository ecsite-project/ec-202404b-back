package com.example.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 追加した商品を削除するDTO.
 *
 * @author takeru.chugun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class deleteItemDto {
    /** 削除する商品のUUID(String) */
    private String orderItemId;
}
