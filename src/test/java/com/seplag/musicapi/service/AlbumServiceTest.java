package com.seplag.musicapi.service;

import com.seplag.musicapi.dominio.entidade.Album;
import com.seplag.musicapi.dominio.entidade.Artista;
import com.seplag.musicapi.dto.AlbumRequestDTO;
import com.seplag.musicapi.repository.AlbumRepository;
import com.seplag.musicapi.repository.ArtistaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AlbumServiceTest {

    @InjectMocks
    private AlbumService albumService;

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private ArtistaRepository artistaRepository;

    @Test
    void deveCriarAlbumComArtistas() {
        Artista artista = new Artista();
        artista.setId(1L);
        artista.setNome("Artista Teste");

        AlbumRequestDTO dto = new AlbumRequestDTO(
                "Álbum Teste",
                Set.of(1L)
        );

        when(artistaRepository.findAllById(dto.artistasIds()))
                .thenReturn(List.of(artista));

        when(albumRepository.save(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        var response = albumService.criar(dto);

        assertEquals("Álbum Teste", response.nome());
        assertEquals(1, response.artistas().size());
    }

    @Test
    void deveLancarExcecaoAoBuscarAlbumInexistente() {
        lenient().when(albumRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> albumService.buscarPorId(99L));
    }

    @Test
    void deveLancarExcecaoAoRemoverAlbumInexistente() {
        when(albumRepository.findById(50L))
                .thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> albumService.remover(50L));
    }
}
