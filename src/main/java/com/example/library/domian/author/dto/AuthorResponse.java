package com.example.library.domian.author.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;


@Setter
@Getter
public class AuthorResponse {

    private UUID id;
    private String name;
    private LocalDate birthDate;
    private String biography;

}
