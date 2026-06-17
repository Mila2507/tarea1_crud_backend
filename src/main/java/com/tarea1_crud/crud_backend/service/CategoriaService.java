package com.tarea1_crud.crud_backend.service;

import com.tarea1_crud.crud_backend.entity.Categoria;
import com.tarea1_crud.crud_backend.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    public Categoria guardar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria actualizar(Long id, Categoria categoria) {
        Categoria existing = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        existing.setNombre(categoria.getNombre());
        existing.setDescripcion(categoria.getDescripcion());
        return categoriaRepository.save(existing);
    }

    public void eliminar(Long id) {
        categoriaRepository.deleteById(id);
    }
}