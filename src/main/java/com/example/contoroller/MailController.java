package com.example.contoroller;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Order;
import com.example.domain.OrderStatus;
import com.example.domain.TimeRange;
import com.example.service.MailService;

/**
 * Mail Serviceをテストするため
 */
@RestController
@RequestMapping("")
public class MailController {

    private final MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/sendEmail")
    public String sendEmail() {
        Order newOrder = new Order();
        // Example data for demonstration
        newOrder.setId(UUID.randomUUID());
        newOrder.setUserId(UUID.randomUUID());
        newOrder.setStatus(OrderStatus.SHIPPED);
        newOrder.setTotalPrice(5000);
        newOrder.setOrderDate(LocalDate.now());
        newOrder.setDestinationName("John Doe");
        newOrder.setDestinationEmail("nitendoaccjp@gmail.com");
        newOrder.setDestinationZipcode("1234567");
        newOrder.setDestinationPrefecture("Tokyo");
        newOrder.setDestinationMunicipalities("Shibuya");
        newOrder.setDestinationAddress("1-2-3 Example Street");
        newOrder.setDestinationTel("012-345-6789");
        newOrder.setDeliveryDate(LocalDate.now().plusDays(3));
        newOrder.setTimeRange(TimeRange.RANGE_10_12);
        newOrder.setPaymentMethod("Credit Card");
        newOrder.setCreatedAt(Instant.now());
        newOrder.setUpdatedAt(Instant.now());
        mailService.sendHtmlMessage(newOrder);
        return "メールが送信されました。";
    }
}
