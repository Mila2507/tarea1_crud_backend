package com.tarea1_crud.crud_backend.repository;

import com.tarea1_crud.crud_backend.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}