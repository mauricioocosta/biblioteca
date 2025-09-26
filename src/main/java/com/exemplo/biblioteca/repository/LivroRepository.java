package com.exemplo.biblioteca.repository;

import com.exemplo.biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    // Métodos customizados usando Spring Data JPA
    List<Livro> findByTituloContaining(String titulo);
    List<Livro> findByAutorContaining(String autor);
}