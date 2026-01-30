package com.seplag.musicapi.repository;

import com.seplag.musicapi.dominio.entidade.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    @Query("""
        select distinct a
        from Album a
        left join fetch a.artistas
    """)
    Page<Album> findAllWithArtistas(Pageable pageable);
}
