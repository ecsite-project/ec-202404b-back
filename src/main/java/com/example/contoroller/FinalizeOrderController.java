
package com.example.contoroller;

import com.example.dtos.FinalizeOrderDto;
import com.example.dtos.PaymentInfoDTO;
import com.example.service.FinalizeOrderService;
import org.hibernate.type.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public record RequestInfo( FinalizeOrderDto form, PaymentInfoDTO paymentInfo){}
    @PostMapping("/finalize")
    public ResponseEntity<?> finalize(@RequestBody RequestInfo requestInfo){
        try{
            FinalizeOrderDto form = requestInfo.form();
            PaymentInfoDTO paymentInfo = requestInfo.paymentInfo();
            return ResponseEntity.ok(finalizeOrderService.finalize(form,paymentInfo));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
