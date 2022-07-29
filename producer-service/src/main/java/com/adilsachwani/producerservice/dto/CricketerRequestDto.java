package com.adilsachwani.producerservice.dto;

import com.adilsachwani.producerservice.constant.Gender;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CricketerRequestDto {

    private Gender gender;
    private String name;
    private String country;
    private LocalDate dateOfBirth;
    private LocalDate debutDate;

}
