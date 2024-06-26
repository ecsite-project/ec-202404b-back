package com.example.service;

import com.example.configuration.MailTask;
import com.example.domain.Order;
import com.example.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * @author mao
 */
@Service
public class AsyncMail {
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    public void sendAsyncMail(Order order, User user){
        MailTask mailTask = new MailTask(order, user);
        taskExecutor.execute(mailTask);
    }
}
