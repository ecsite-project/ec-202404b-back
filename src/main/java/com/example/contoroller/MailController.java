package com.example.contoroller;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import com.example.repository.OrderRepository;
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

    @Autowired
    OrderRepository orderRepository;
    @GetMapping("/sendEmail")
    public String sendEmail() {
        Order newOrder = orderRepository.findByTotalPrice(320000);
        newOrder.setDestinationEmail("nitendoaccjp@gmail.com");
        mailService.sendHtmlMessage(newOrder);
        return "メールが送信されました。";
    }
}
