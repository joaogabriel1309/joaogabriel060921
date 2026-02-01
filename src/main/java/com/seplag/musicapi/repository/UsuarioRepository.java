package com.seplag.musicapi.repository;

import com.seplag.musicapi.dominio.entidade.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsernameAndAtivoTrue(String username);
}
