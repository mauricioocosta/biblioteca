package com.exemplo.biblioteca;

import com.exemplo.biblioteca.repository.BibliotecaDAO;
import com.exemplo.biblioteca.model.Livro;
import com.exemplo.biblioteca.exception.LivroNaoEncontradoException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class BibliotecaApplication {

    public static void main(String[] args) {
        // Inicializa o Spring Boot
        SpringApplication.run(BibliotecaApplication.class, args);

        // Mantemos o menu tradicional com Scanner
        BibliotecaDAO dao = new BibliotecaDAO();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Biblioteca (JDBC) ===");
            System.out.println("1 - Listar livros");
            System.out.println("2 - Adicionar livro");
            System.out.println("3 - Buscar livros");
            System.out.println("4 - Remover livro");
            System.out.println("5 - Sair");
            System.out.print("Escolha: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    List<Livro> livros = dao.listarLivros();
                    if (livros.isEmpty()) {
                        System.out.println("Nenhum livro encontrado.");
                    } else {
                        livros.forEach(System.out::println);
                    }
                    break;

                case 2:
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();
                    System.out.print("Autor: ");
                    String autor = sc.nextLine();
                    System.out.print("Ano: ");
                    int ano = sc.nextInt();
                    sc.nextLine();
                    dao.adicionarLivro(new Livro(titulo, autor, ano));
                    System.out.println("Livro adicionado!");
                    break;

                case 3:
                    System.out.println("1 - Buscar por título");
                    System.out.println("2 - Buscar por autor");
                    int busca = sc.nextInt();
                    sc.nextLine();
                    if (busca == 1) {
                        System.out.print("Digite o título: ");
                        String tBusca = sc.nextLine();
                        dao.buscarPorTitulo(tBusca).forEach(System.out::println);
                    } else if (busca == 2) {
                        System.out.print("Digite o autor: ");
                        String aBusca = sc.nextLine();
                        dao.buscarPorAutor(aBusca).forEach(System.out::println);
                    } else {
                        System.out.println("Opção inválida!");
                    }
                    break;

                case 4:
                    System.out.print("Digite o ID do livro para remover: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    try {
                        dao.removerLivro(id);
                        System.out.println("Livro removido!");
                    } catch (LivroNaoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("Saindo...");
                    sc.close();
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
