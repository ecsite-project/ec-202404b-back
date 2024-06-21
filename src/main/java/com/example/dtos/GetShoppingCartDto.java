package com.example.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ショッピングカートを表示するときのDTOクラス.
 *
 * @author takeru.chugun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetShoppingCartDto {
    /** ユーザのid */
    private String userId;
}
