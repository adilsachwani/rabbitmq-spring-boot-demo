package com.adilsachwani.producerservice.service;

import com.adilsachwani.producerservice.dto.CricketerRequestDto;
import com.adilsachwani.producerservice.dto.CricketerResponseDto;

public interface CricketerService {

    CricketerResponseDto saveCricketer(CricketerRequestDto cricketerRequestDto);

}
