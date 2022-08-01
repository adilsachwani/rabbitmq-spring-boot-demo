package com.adilsachwani.consumerservice.listener;

import com.adilsachwani.consumerservice.config.MessagingQueueConfig;
import com.adilsachwani.consumerservice.dto.CricketerDto;
import com.adilsachwani.consumerservice.service.CricketerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CricketerListener {

    @Autowired
    private CricketerService cricketerService;

    @RabbitListener(queues = MessagingQueueConfig.CRICKETER_QUEUE)
    public void addCricketerListener(CricketerDto cricketerDto){
        cricketerService.saveCricketer(cricketerDto);
    }

}
