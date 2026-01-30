package com.seplag.musicapi.service;

import com.seplag.musicapi.dominio.entidade.Artista;
import com.seplag.musicapi.dto.ArtistaRequestDTO;
import com.seplag.musicapi.dto.ArtistaResponseDTO;
import com.seplag.musicapi.repository.ArtistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistaService {

    private final ArtistaRepository artistaRepository;

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
}
