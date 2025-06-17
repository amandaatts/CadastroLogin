package com.example.cadastro_login.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cadastro_login.entities.Usuario;
import com.example.cadastro_login.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // DTO para login
    public static class LoginRequest {
        public String email;
        public String senha;

        // getters e setters
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getSenha() { return senha; }
        public void setSenha(String senha) { this.senha = senha; }
    }

    // Cadastro de usuário
    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(@Valid @RequestBody Usuario usuario) {
        try {
            Usuario novoUsuario = usuarioService.cadastrar(usuario);
            novoUsuario.setSenha(null); // não retorna a senha
            return ResponseEntity.ok(novoUsuario);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Usuario usuarioAutenticado = usuarioService.autenticar(loginRequest.getEmail(), loginRequest.getSenha());
            usuarioAutenticado.setSenha(null); // não retorna a senha
            return ResponseEntity.ok(usuarioAutenticado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
