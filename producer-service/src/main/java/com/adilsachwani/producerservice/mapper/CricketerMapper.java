package com.adilsachwani.producerservice.mapper;

import com.adilsachwani.producerservice.dto.CricketerRequestDto;
import com.adilsachwani.producerservice.dto.CricketerResponseDto;
import com.adilsachwani.producerservice.model.Cricketer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CricketerMapper {

    Cricketer fromDto(CricketerRequestDto cricketerRequestDto);

    CricketerResponseDto toDto(Cricketer cricketer);

}
