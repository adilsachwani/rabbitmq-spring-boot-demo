package com.adilsachwani.consumerservice.batch;

import com.adilsachwani.consumerservice.mapper.CricketerMapper;
import com.adilsachwani.consumerservice.model.Cricketer;
import com.adilsachwani.consumerservice.model.CricketerCsv;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CricketerProcessor implements ItemProcessor<CricketerCsv, Cricketer> {

    @Autowired
    private CricketerMapper cricketerMapper;

    @Override
    public Cricketer process(CricketerCsv cricketerCsv) {
        log.debug("--- Starting process() ---");
        Cricketer cricketer = cricketerMapper.fromCsv(cricketerCsv);
        log.debug("--- Ending process() ---");
        return cricketer;
    }

}
