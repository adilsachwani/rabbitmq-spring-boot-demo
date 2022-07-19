package com.adilsachwani.consumerservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Cricketer {

    @Id
    private UUID id;
    private String gender;
    private String name;
    private String country;
    private LocalDate dateOfBirth;
    private LocalDate debutDate;

}
