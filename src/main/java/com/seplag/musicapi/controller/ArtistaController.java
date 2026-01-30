package com.seplag.musicapi.controller;

import com.seplag.musicapi.dto.ArtistaRequestDTO;
import com.seplag.musicapi.dto.ArtistaResponseDTO;
import com.seplag.musicapi.service.ArtistaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/artistas")
@RequiredArgsConstructor
public class ArtistaController {

    private final ArtistaService artistaService;

    @PostMapping
    public ResponseEntity<ArtistaResponseDTO> criar(@RequestBody @Valid ArtistaRequestDTO artistaRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(artistaService.criar(artistaRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<ArtistaResponseDTO>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(artistaService.listar());
    }
}
