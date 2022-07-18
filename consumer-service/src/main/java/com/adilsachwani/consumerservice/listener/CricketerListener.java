package com.adilsachwani.consumerservice.listener;

import com.adilsachwani.consumerservice.config.MessagingQueueConfig;
import com.adilsachwani.consumerservice.model.Cricketer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CricketerListener {

    @RabbitListener(queues = MessagingQueueConfig.CRICKETER_QUEUE)
    public void addCricketListener(Cricketer cricketer){
        System.out.println(cricketer.getName());
    }

}
