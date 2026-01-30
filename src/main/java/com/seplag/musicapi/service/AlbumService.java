package com.seplag.musicapi.service;

import com.seplag.musicapi.dominio.entidade.Album;
import com.seplag.musicapi.dominio.entidade.Artista;
import com.seplag.musicapi.dto.AlbumRequestDTO;
import com.seplag.musicapi.dto.AlbumResponseDTO;
import com.seplag.musicapi.dto.ArtistaResponseDTO;
import com.seplag.musicapi.repository.AlbumRepository;
import com.seplag.musicapi.repository.ArtistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistaRepository artistaRepository;

    public AlbumResponseDTO criar(AlbumRequestDTO albumRequestDTO) {

        Set<Artista> artistas = new HashSet<>(artistaRepository.findAllById(albumRequestDTO.artistasIds()));

        Album album = new Album();
        album.setNome(albumRequestDTO.nome());
        album.setArtistas(artistas);

//        for (Artista artista : artistas) {
//            artista.getAlbuns().add(album);
//        }

        album = albumRepository.save(album);

        return new AlbumResponseDTO(
                album.getId(),
                album.getNome(),
                artistas.stream()
                        .map(a -> new ArtistaResponseDTO(a.getId(), a.getNome()))
                        .collect(Collectors.toSet())
        );
    }

    public Page<AlbumResponseDTO> listar(Pageable pageable) {
        return albumRepository.findAllWithArtistas(pageable)
                .map(album -> new AlbumResponseDTO(
                        album.getId(),
                        album.getNome(),
                        album.getArtistas().stream()
                                .map(a -> new ArtistaResponseDTO(a.getId(), a.getNome()))
                                .collect(Collectors.toSet())
                ));
    }
}
