package com.adilsachwani.consumerservice.service.impl;

import com.adilsachwani.consumerservice.dto.CricketerDto;
import com.adilsachwani.consumerservice.mapper.CricketerMapper;
import com.adilsachwani.consumerservice.model.Cricketer;
import com.adilsachwani.consumerservice.repository.CricketerRepository;
import com.adilsachwani.consumerservice.service.CricketerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CricketerServiceImpl implements CricketerService {

    @Autowired
    private CricketerRepository cricketerRepository;

    @Autowired
    private CricketerMapper cricketerMapper;

    @Override
    public CricketerDto saveCricketer(CricketerDto cricketerDto) {
        log.info("--- Starting saveCricketer() ---");
        Cricketer cricketer = cricketerMapper.fromDto(cricketerDto);
        log.info("--- Ending saveCricketer() ---");
        return cricketerMapper.toDto(cricketerRepository.save(cricketer));
    }

}
