package com.tarea1_crud.crud_backend.service;

import com.tarea1_crud.crud_backend.entity.Rol;
import com.tarea1_crud.crud_backend.entity.Usuario;
import com.tarea1_crud.crud_backend.repository.RolRepository;
import com.tarea1_crud.crud_backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario registrar(String username, String password) {
        if (usuarioRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }

        Rol userRol = rolRepository.findByNombre("USER")
                .orElseThrow(() -> new IllegalStateException("Rol USER no configurado"));

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(passwordEncoder.encode(password));
        usuario.setRol(userRol);
        return usuarioRepository.save(usuario);
    }
}
