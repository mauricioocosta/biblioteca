package com.exemplo.biblioteca.exception;

/**
 * Exceção customizada para quando um livro não for encontrado
 */
public class LivroNaoEncontradoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LivroNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}