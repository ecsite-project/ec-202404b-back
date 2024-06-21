package com.example.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mao
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInfoDTO {
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("order_number")
    private String orderNumber;
    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("card_number")
    private String cardNumber;
    @JsonProperty("card_exp_year")
    private Integer cardExpYear;
    @JsonProperty("card_exp_month")
    private Integer cardExpMonth;
    @JsonProperty("card_name")
    private String cardName;
    @JsonProperty("card_cvv")
    private String cardCvv;
}
