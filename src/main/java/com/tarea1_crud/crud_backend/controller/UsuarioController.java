package com.tarea1_crud.crud_backend.controller;

import com.tarea1_crud.crud_backend.entity.Usuario;
import com.tarea1_crud.crud_backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    public record UsuarioResponseDTO(Long id, String username, String rol) {
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        List<UsuarioResponseDTO> usuarios = usuarioService.listar().stream()
                .map(u -> new UsuarioResponseDTO(u.getId(), u.getUsername(), u.getRol().getNombre()))
                .toList();
        return ResponseEntity.ok(usuarios);
    }
}
