package com.tarea1_crud.crud_backend.config;

import com.tarea1_crud.crud_backend.entity.Rol;
import com.tarea1_crud.crud_backend.entity.Usuario;
import com.tarea1_crud.crud_backend.repository.RolRepository;
import com.tarea1_crud.crud_backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initData() {
        return args -> {


            Rol adminRol = rolRepository.findByNombre("SUPER-ADMIN-ROLE")
                    .orElseGet(() -> {
                        Rol r = new Rol();
                        r.setNombre("SUPER-ADMIN-ROLE");
                        return rolRepository.save(r);
                    });

            Rol userRol = rolRepository.findByNombre("USER")
                    .orElseGet(() -> {
                        Rol r = new Rol();
                        r.setNombre("USER");
                        return rolRepository.save(r);
                    });


            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin1234"));
                admin.setRol(adminRol);
                usuarioRepository.save(admin);
            }

            if (usuarioRepository.findByUsername("user").isEmpty()) {
                Usuario user = new Usuario();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("user1234"));
                user.setRol(userRol);
                usuarioRepository.save(user);
            }

            System.out.println("✅ Datos iniciales cargados correctamente");
        };
    }
}