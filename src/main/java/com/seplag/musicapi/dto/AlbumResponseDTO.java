package com.seplag.musicapi.dto;

import com.seplag.musicapi.dominio.entidade.Album;

import java.util.Set;
import java.util.stream.Collectors;

public record AlbumResponseDTO(
        Long id,
        String nome,
        Set<ArtistaResponseDTO> artistas
) {

    public static AlbumResponseDTO fromEntity(Album album) {
        return new AlbumResponseDTO(
                album.getId(),
                album.getNome(),
                album.getArtistas().stream()
                        .map(a -> new ArtistaResponseDTO(a.getId(), a.getNome()))
                        .collect(Collectors.toSet())
        );
    }

}
