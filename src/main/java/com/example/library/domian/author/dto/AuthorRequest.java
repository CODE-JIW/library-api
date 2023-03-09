package com.example.library.domian.author.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Setter
@Getter
public class AuthorRequest {

    @NotBlank
    @Size(min = 5, max = 100)
    private String name;
    @NotNull
    private LocalDate birthDate;
    @NotBlank
    @Size(min = 5, max = 1000)
    private String biography;
}
