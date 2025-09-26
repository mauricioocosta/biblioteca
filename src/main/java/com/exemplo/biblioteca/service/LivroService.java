package com.exemplo.biblioteca.service;

import com.exemplo.biblioteca.model.Livro;
import com.exemplo.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Serviço responsável pelas regras de negócio relacionadas a livros.
 * Atua como camada intermediária entre o Controller e o Repository.
 */
@Service
public class LivroService {

    private final LivroRepository livroRepository;

    /**
     * Construtor que injeta o LivroRepository.
     * @param livroRepository Repositório de livros para persistência no banco.
     */
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    /**
     * Lista todos os livros cadastrados no banco.
     * @return Lista de livros
     */
    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }

    /**
     * Busca um livro pelo seu ID.
     * @param id Identificador do livro
     * @return Optional contendo o livro caso encontrado, ou vazio se não encontrado
     */
    public Optional<Livro> buscarPorId(Long id) {
        return livroRepository.findById(id);
    }

    /**
     * Salva um livro no banco.
     * Se o livro tiver ID, ele será atualizado; se não, será criado.
     * @param livro Livro a ser salvo
     * @return Livro salvo
     */
    public Livro salvar(Livro livro) {
        return livroRepository.save(livro);
    }

    /**
     * Remove um livro pelo seu ID.
     * @param id Identificador do livro a ser removido
     */
    public void deletar(Long id) {
        livroRepository.deleteById(id);
    }

    /**
     * Busca livros pelo título contendo a string informada.
     * @param titulo Parte do título para busca
     * @return Lista de livros que possuem o título correspondente
     */
    public List<Livro> buscarPorTitulo(String titulo) {
        // Aqui podemos implementar um método customizado no Repository usando Spring Data JPA
        return livroRepository.findAll().stream()
                .filter(l -> l.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .toList();
    }

    /**
     * Busca livros pelo autor contendo a string informada.
     * @param autor Parte do nome do autor para busca
     * @return Lista de livros que possuem o autor correspondente
     */
    public List<Livro> buscarPorAutor(String autor) {
        return livroRepository.findAll().stream()
                .filter(l -> l.getAutor().toLowerCase().contains(autor.toLowerCase()))
                .toList();
    }
}