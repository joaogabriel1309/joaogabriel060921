package com.seplag.musicapi.controller;

import com.seplag.musicapi.dto.AlbumRequestDTO;
import com.seplag.musicapi.dto.AlbumResponseDTO;
import com.seplag.musicapi.service.AlbumService;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/albuns")
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    @PostMapping
    public ResponseEntity<AlbumResponseDTO> criar(@RequestBody @Valid AlbumRequestDTO albumRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(albumService.criar(albumRequestDTO));
    }

    @GetMapping
    public ResponseEntity<Page<AlbumResponseDTO>> listar(
            @PageableDefault(size = 10, sort = "nome") Pageable pageable
    ) {
        return ResponseEntity.ok(albumService.listar(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlbumResponseDTO> editar(@PathVariable Long id, @RequestBody @Valid AlbumRequestDTO albumRequestDTO) {
        return ResponseEntity.ok(albumService.atualizar(id, albumRequestDTO));
    }
}
