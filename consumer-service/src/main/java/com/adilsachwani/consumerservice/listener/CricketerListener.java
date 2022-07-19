package com.adilsachwani.consumerservice.listener;

import com.adilsachwani.consumerservice.config.MessagingQueueConfig;
import com.adilsachwani.consumerservice.model.Cricketer;
import com.adilsachwani.consumerservice.repository.CricketerRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CricketerListener {

    @Autowired
    private CricketerRepository cricketerRepository;

    @RabbitListener(queues = MessagingQueueConfig.CRICKETER_QUEUE)
    public void addCricketListener(Cricketer cricketer){
        System.out.println(cricketer.getName());
        cricketerRepository.save(cricketer);
    }

}
