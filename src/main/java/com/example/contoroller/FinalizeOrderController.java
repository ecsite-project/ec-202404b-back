
package com.example.contoroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.FinalizeOrderDto;
import com.example.dtos.PaymentInfoDTO;
import com.example.repositories.UserRepository;
import com.example.security.JWTAuthenticationToken.AuthenticationUser;
import com.example.service.AsyncMail;
import com.example.service.FinalizeOrderService;
import com.example.service.MailService;

import lombok.val;

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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AsyncMail asyncMail;

    public record RequestInfo(FinalizeOrderDto form, PaymentInfoDTO paymentInfo) {
    }

    @PostMapping("/finalize")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> finalized(@RequestBody RequestInfo requestInfo,
            @AuthenticationPrincipal AuthenticationUser user) {
        try {
            FinalizeOrderDto form = requestInfo.form();
            form.setUserId(user.id());
            PaymentInfoDTO paymentInfo = requestInfo.paymentInfo();
            val order = finalizeOrderService.finalize(form, paymentInfo);
            if (order != null) {
                asyncMail.sendAsyncMail(order,userRepository.findById(order.getUserId()).orElse(null));
                return ResponseEntity.ok("success");
            }
            return ResponseEntity.badRequest().body("error");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
