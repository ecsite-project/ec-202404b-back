package com.example.configuration;

import com.example.domain.Order;
import com.example.domain.User;
import com.example.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author mao
 */
public class MailTask implements Runnable {

    private Order order;
    private User user;

    @Autowired
    private  MailService mailService;

    public MailTask (Order order, User user){
        this.order = order;
        this.user = user;
    }

    @Override
    public void run() {
        mailService.sendHtmlMessage(order, user);
    }
}
