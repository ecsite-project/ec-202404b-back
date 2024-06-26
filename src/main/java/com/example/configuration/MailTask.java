package com.example.configuration;

import com.example.domain.Order;
import com.example.domain.User;
import com.example.service.MailService;

import lombok.val;

/**
 * @author mao
 */
public class MailTask implements Runnable {

    private Order order;
    private User user;


    public MailTask (Order order, User user){
        this.order = order;
        this.user = user;
    }

    @Override
    public void run() {
        val mailService = new MailService();
        mailService.sendHtmlMessage(order, user);
    }
}
