package com.seplag.musicapi.repository;

import com.seplag.musicapi.dominio.entidade.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
}
