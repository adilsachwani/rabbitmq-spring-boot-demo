package com.adilsachwani.producerservice.service.impl;

import com.adilsachwani.producerservice.config.MessagingQueueConfig;
import com.adilsachwani.producerservice.dto.CricketerRequestDto;
import com.adilsachwani.producerservice.dto.CricketerResponseDto;
import com.adilsachwani.producerservice.mapper.CricketerMapper;
import com.adilsachwani.producerservice.model.Cricketer;
import com.adilsachwani.producerservice.service.CricketerService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CricketerServiceImpl implements CricketerService {

    @Autowired
    private CricketerMapper cricketerMapper;

    @Autowired
    private RabbitTemplate template;

    @Override
    public CricketerResponseDto saveCricketer(CricketerRequestDto cricketerRequestDto) {
        Cricketer cricketer = cricketerMapper.fromDto(cricketerRequestDto);
        cricketer.setId(UUID.randomUUID());
        template.convertAndSend(MessagingQueueConfig.CRICKETER_EXCHANGE,
                MessagingQueueConfig.CRICKETER_ROUTING_KEY, cricketer);
        return cricketerMapper.toDto(cricketer);
    }

}
