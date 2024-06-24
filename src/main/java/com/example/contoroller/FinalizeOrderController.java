
package com.example.contoroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.FinalizeOrderDto;
import com.example.dtos.PaymentInfoDTO;
import com.example.service.FinalizeOrderService;

/**
 * 注文確認画面を操作するコントローラクラス.
 *
 * @author takeru.chugun
 */
@RestController
@RequestMapping("/api/confirm")
public class FinalizeOrderController {
    @Autowired
    private FinalizeOrderService finalizeOrderService;

//    public record RequestInfo( FinalizeOrderDto form, PaymentInfoDTO paymentInfo){}
//    @RequestBody RequestInfo requestInfo
//    FinalizeOrderDto form = requestInfo.form();
//    PaymentInfoDTO paymentInfo = requestInfo.paymentInfo();
    @GetMapping("/finalize")
    public ResponseEntity<?> finalized(){
        try{
            FinalizeOrderDto form = new FinalizeOrderDto();
            PaymentInfoDTO paymentInfo = new PaymentInfoDTO();
//            formの作成
            form.setUserId("e091ed15-efa4-4be6-8d71-400835e35e5b");
            form.setName("中郡");
            form.setEmail("rakus@co.jp");
            form.setZipcode("123-4567");
            form.setPrefecture("東京都");
            form.setMunicipalities("新宿区");
            form.setAddress("新宿1-2-3");
            form.setTelephone("08012345678");
            form.setDeliveryDate("2024-11-12");
            form.setDeliveryTime("8:00~10:00");
            form.setPaymentMethod("Credit Card");

            // クレカ用のフォームの作成
            paymentInfo.setUserId("e091ed15-efa4-4be6-8d71-400835e35e5b");
            paymentInfo.setOrderNumber("4316ab38-9f4c-448b-b78d-076ced054691");
            paymentInfo.setAmount(20000);
            paymentInfo.setCardNumber("1111222233334444");
            paymentInfo.setCardExpYear(2025);
            paymentInfo.setCardExpMonth(10);
            paymentInfo.setCardName("TAKERU CHUGUN");
            paymentInfo.setCardCvv("123");


            return ResponseEntity.ok(finalizeOrderService.finalize(form,paymentInfo));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
