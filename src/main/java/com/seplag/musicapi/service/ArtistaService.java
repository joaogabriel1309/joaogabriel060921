package com.seplag.musicapi.service;

import com.seplag.musicapi.dominio.entidade.Artista;
import com.seplag.musicapi.dto.ArtistaRequestDTO;
import com.seplag.musicapi.dto.ArtistaResponseDTO;
import com.seplag.musicapi.repository.AlbumRepository;
import com.seplag.musicapi.repository.ArtistaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistaService {

    private final ArtistaRepository artistaRepository;
    private final AlbumRepository albumRepository;

    public ArtistaResponseDTO criar(ArtistaRequestDTO dto) {
        Artista artista = new Artista();
        artista.setNome(dto.nome());
        artista = artistaRepository.save(artista);
        return new ArtistaResponseDTO(artista.getId(), artista.getNome());
    }

    public List<ArtistaResponseDTO> listar() {
        return artistaRepository.findAll()
                .stream()
                .map(a -> new ArtistaResponseDTO(a.getId(), a.getNome()))
                .toList();
    }

    public ArtistaResponseDTO buscarPorId(Long id) {
        Artista artista = artistaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Artista não encontrado"));
        return new ArtistaResponseDTO(artista.getId(), artista.getNome());
    }

    public ArtistaResponseDTO atualizar(Long id, ArtistaRequestDTO dto) {
        Artista artista = artistaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Artista não encontrado"));

        artista.setNome(dto.nome());
        Artista atualizado = artistaRepository.save(artista);

        return new ArtistaResponseDTO(atualizado.getId(), atualizado.getNome());
    }

    public void remover(Long id) {

        if (!artistaRepository.existsById(id)) {
            throw new EntityNotFoundException("Artista não encontrado");
        }

        if (albumRepository.existsByArtistasId(id)) {
            throw new IllegalStateException(
                    "Não é possível excluir o artista pois ele possui álbuns associados");
        }

        artistaRepository.deleteById(id);
    }

}
