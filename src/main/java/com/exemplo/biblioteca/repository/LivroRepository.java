package main.java.com.exemplo.biblioteca.repository;

import main.java.com.exemplo.biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
