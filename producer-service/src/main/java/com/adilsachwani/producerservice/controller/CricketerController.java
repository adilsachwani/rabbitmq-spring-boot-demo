package com.adilsachwani.producerservice.controller;

import com.adilsachwani.producerservice.config.MessagingQueueConfig;
import com.adilsachwani.producerservice.model.Cricketer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/cricketer")
public class CricketerController {

    @Autowired
    private RabbitTemplate template;

    @PostMapping
    public Cricketer publishCricketer(@RequestBody Cricketer cricketer){
        cricketer.setId(UUID.randomUUID());
        template.convertAndSend(MessagingQueueConfig.CRICKETER_EXCHANGE_TOPIC,
                MessagingQueueConfig.CRICKETER_ROUTING_KEY, cricketer);
        return cricketer;
    }

}
