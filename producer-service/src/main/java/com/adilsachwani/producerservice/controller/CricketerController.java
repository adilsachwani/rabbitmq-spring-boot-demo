package com.adilsachwani.producerservice.controller;

import com.adilsachwani.producerservice.dto.CricketerRequestDto;
import com.adilsachwani.producerservice.dto.CricketerResponseDto;
import com.adilsachwani.producerservice.service.CricketerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cricketer")
public class CricketerController {

    @Autowired
    private CricketerService cricketerService;

    @PostMapping
    public ResponseEntity<CricketerResponseDto> publishCricketer(@RequestBody CricketerRequestDto cricketerRequestDto){
        CricketerResponseDto cricketer = cricketerService.saveCricketer(cricketerRequestDto);
        return ResponseEntity.ok(cricketer);
    }

}
