package com.example.domain;

public enum OrderStatus {
    BEFORE_ORDER(0, "注文前"),
    UNPAID(1, "未入金"),
    PAID(2, "入金済"),
    SHIPPED(3, "発送済"),
    DELIVERED(4, "配送完了"),
    CANCELLED(9, "キャンセル");

    private final int code;
    private final String description;

    OrderStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static OrderStatus fromCode(int code) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }

    public String toString() {
        return "" + code;
    }
}