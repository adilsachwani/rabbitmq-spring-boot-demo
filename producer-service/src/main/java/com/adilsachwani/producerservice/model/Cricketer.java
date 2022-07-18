package com.adilsachwani.producerservice.model;

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
    private String gender;
    private String name;
    private String country;
    private LocalDate dateOfBirth;
    private LocalDate debutDate;

}
