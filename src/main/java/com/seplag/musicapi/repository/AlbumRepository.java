package com.seplag.musicapi.repository;

import com.seplag.musicapi.dominio.entidade.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    @Query("""
        select a
        from Album a
        left join fetch a.artistas
    """)
    Page<Album> findAllWithArtista(Pageable pageable);

    @Query("""
        select a
        from Album a
        left join fetch a.artistas
        where a.id = :id
    """)
    Optional<Album> findByIdWithArtista(Long id);

    boolean existsByArtistasId(Long artistaId);
}
