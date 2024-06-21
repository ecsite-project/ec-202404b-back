package com.example.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ページング情報を要求するフォームドメイン.
 * 
 * @author char5742
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDto {
    /** 現在のページ番号 */
    Integer currentPage;
    /** ページあたりのレコード数 */
    Integer perPage;
}
