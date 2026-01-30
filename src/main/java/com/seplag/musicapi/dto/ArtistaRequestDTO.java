package com.seplag.musicapi.dto;

import jakarta.validation.constraints.NotBlank;

public record ArtistaRequestDTO(
        @NotBlank String nome
) {

}
