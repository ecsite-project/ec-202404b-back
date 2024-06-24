package com.example.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ページング情報のレスポンスドメイン.
 * 
 * @param <T> ページング対象のドメイン
 * @author char5742
 */
@Data
@AllArgsConstructor
public final class PageResponseDto<T> {
    /** メタデータ */
    private Metadata metadata;
    /** レコード */
    private List<T> records;

    @Data
    @AllArgsConstructor
    public static final class Metadata {
        /** 現在のページ番号 */
        int currentPage;
        /** ページあたりのレコード数 */
        int perPage;
        /** 最後のページ番号 */
        int lastPage;
        /** 総レコード数 */
        int total;

    }

}
