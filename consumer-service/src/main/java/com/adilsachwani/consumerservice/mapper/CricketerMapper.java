package com.adilsachwani.consumerservice.mapper;

import com.adilsachwani.consumerservice.model.Cricketer;
import com.adilsachwani.consumerservice.model.CricketerCsv;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CricketerMapper {

    Cricketer fromCsv(CricketerCsv cricketerCsv);

}
