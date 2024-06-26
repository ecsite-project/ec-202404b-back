package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.example.configuration.MailTask;
import com.example.domain.Order;
import com.example.domain.User;

/**
 * @author mao
 */
@Service
public class AsyncMail {
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private MailTask mailTask;

    public void sendAsyncMail(Order order, User user){
        mailTask.setOrder(order);
        mailTask.setUser(user);
        taskExecutor.execute(mailTask);
    }


}
