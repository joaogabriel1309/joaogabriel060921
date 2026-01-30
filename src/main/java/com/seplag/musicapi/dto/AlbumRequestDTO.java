package com.seplag.musicapi.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record AlbumRequestDTO(
        @NotBlank String nome,
        Set<Long> artistasIds
) {
}
