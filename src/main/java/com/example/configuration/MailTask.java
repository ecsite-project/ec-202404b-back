package com.example.configuration;

import com.example.domain.Order;
import com.example.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author mao
 */
public class MailTask implements Runnable {

    private Order order;

    @Autowired
    private  MailService mailService;

    public MailTask (Order order){
        this.order = order;
    }

    @Override
    public void run() {
        mailService.sendHtmlMessage(order);
    }
}
