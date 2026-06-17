package com.tarea1_crud.crud_backend.repository;

import com.tarea1_crud.crud_backend.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}