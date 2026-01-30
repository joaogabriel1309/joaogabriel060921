package com.seplag.musicapi.service;

import com.seplag.musicapi.dominio.entidade.Album;
import com.seplag.musicapi.dominio.entidade.Artista;
import com.seplag.musicapi.dto.AlbumRequestDTO;
import com.seplag.musicapi.dto.AlbumResponseDTO;
import com.seplag.musicapi.dto.ArtistaResponseDTO;
import com.seplag.musicapi.repository.AlbumRepository;
import com.seplag.musicapi.repository.ArtistaRepository;
import jakarta.transaction.Transactional;
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

        album = albumRepository.save(album);

        return AlbumResponseDTO.fromEntity(album);
    }

    public Page<AlbumResponseDTO> listar(Pageable pageable) {
        return albumRepository.findAllWithArtistas(pageable)
                .map(AlbumResponseDTO::fromEntity);
    }

    @Transactional
    public AlbumResponseDTO atualizar(Long albumId, AlbumRequestDTO albumRequestDTO) {
      Album album = albumRepository.findById(albumId).orElseThrow(() -> new RuntimeException("Álbum não encontrado"));

      album.setNome(albumRequestDTO.nome());

      album.getArtistas().forEach(a -> a.getAlbuns().remove(album));
      album.getArtistas().clear();

      Set<Artista> artistas = new HashSet<>(artistaRepository.findAllById(albumRequestDTO.artistasIds()));

        artistas.forEach(artista -> {
            artista.getAlbuns().add(album);
            album.getArtistas().add(artista);
        });

        return AlbumResponseDTO.fromEntity(album);
    }
}
