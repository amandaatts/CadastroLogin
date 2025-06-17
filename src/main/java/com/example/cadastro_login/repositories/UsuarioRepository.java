package com.example.cadastro_login.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cadastro_login.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar usuário pelo email (usado para login)
    Optional<Usuario> findByEmail(String email);

    // Verificar se já existe um usuário com determinado e-mail
    boolean existsByEmail(String email);
}
