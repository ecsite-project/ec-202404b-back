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
public class CreditCardAPIResponseDTO {
    private String status;
    private String message;
    @JsonProperty("error_code")
    private String errorCode;
}
