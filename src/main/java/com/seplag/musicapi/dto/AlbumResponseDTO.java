package com.seplag.musicapi.dto;

import java.util.Set;

public record AlbumResponseDTO(
        Long id,
        String nome,
        Set<ArtistaResponseDTO> artistas
) {}

