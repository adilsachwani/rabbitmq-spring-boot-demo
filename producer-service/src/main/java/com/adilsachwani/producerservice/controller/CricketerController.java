package com.adilsachwani.producerservice.controller;

import com.adilsachwani.producerservice.config.MessagingQueueConfig;
import com.adilsachwani.producerservice.model.Cricketer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/cricketer")
public class CricketerController {

    @Autowired
    private RabbitTemplate template;

    @PostMapping
    public ResponseEntity<Void> publishCricketer(@RequestBody Cricketer cricketer){
        cricketer.setId(UUID.randomUUID());
        template.convertAndSend(MessagingQueueConfig.CRICKETER_EXCHANGE,
                MessagingQueueConfig.CRICKETER_ROUTING_KEY, cricketer);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/import/{country}")
    public ResponseEntity<Void> importCricketers(@PathVariable(name = "country") String country){
        template.convertAndSend(MessagingQueueConfig.CRICKETER_EXCHANGE,
                MessagingQueueConfig.IMPORT_CRICKETERS_ROUTING_KEY, country);
        return ResponseEntity.noContent().build();
    }

}
