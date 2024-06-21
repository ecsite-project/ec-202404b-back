package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.dtos.CreditCardAPIResponseDTO;
import com.example.dtos.PaymentInfoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author mao
 */
@Service
public class CreditCardService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public CreditCardAPIResponseDTO callApi(PaymentInfoDTO paymentInfoDTO) throws JsonProcessingException {
        String CREDIT_CARD_SERVICE_URL = "http://153.127.48.168:8080/sample-credit-card-web-api/credit-card/payment"; // 外部APIのURL

        // リクエストヘッダーの設定
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // リクエストボディの設定
        String requestBody = objectMapper.writeValueAsString(paymentInfoDTO);
        System.out.println(requestBody);
        // HTTPエンティティの作成
        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);

        // POSTリクエストの送信,　レスポンスの返し
        return restTemplate.exchange(
                CREDIT_CARD_SERVICE_URL, HttpMethod.POST, httpEntity, CreditCardAPIResponseDTO.class).getBody();
    }
}
