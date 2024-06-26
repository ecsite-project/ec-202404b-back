package com.example.service;

import com.example.configuration.MailTask;
import com.example.domain.Order;
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

    public void sendAsyncMail(Order order){
        MailTask mailTask = new MailTask(order);
        taskExecutor.execute(mailTask);
    }
}
