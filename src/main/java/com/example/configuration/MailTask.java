package com.example.configuration;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.domain.Order;
import com.example.domain.User;
import com.example.service.MailService;

/**
 * @author mao
 */
public class MailTask implements Runnable {

    private Order order;
    private User user;

    @Autowired
    private MailService mailService;

    @Override
    public void run() {
        mailService.sendHtmlMessage(order, user);
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
