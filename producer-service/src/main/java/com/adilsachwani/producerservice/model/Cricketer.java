package com.adilsachwani.producerservice.model;

import com.adilsachwani.producerservice.constant.Gender;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cricketer {

    private UUID id;
    private Gender gender;
    private String name;
    private String country;
    private LocalDate dateOfBirth;
    private LocalDate debutDate;

}
